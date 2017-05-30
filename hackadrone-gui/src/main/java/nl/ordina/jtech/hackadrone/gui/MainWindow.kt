package nl.ordina.jtech.hackadrone.gui

import nl.ordina.jtech.hackadrone.CX10
import nl.ordina.jtech.hackadrone.Drone
import nl.ordina.jtech.hackadrone.DroneException
import nl.ordina.jtech.hackadrone.io.Keyboard
import nl.ordina.jtech.hackadrone.utils.ANSI
import nl.ordina.jtech.hackadrone.utils.SpecialColor

import javax.swing.*

import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.IOException
import java.util.function.Consumer
import kotlin.concurrent.thread

/**
 * Class representing a main window that exists within a Graphical User Interface (GUI).

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 */
class MainWindow
/**
 * A main window constructor.
 */
internal constructor() : JFrame(), Frame, ActionListener, ClickEvent {

    /** The core code of the CX10 drone. */
    private val cx10 = CX10()

    /** The main window model. */
    private var model = initModel()

    /** The default panel. */
    private lateinit var panel: JPanel

    /** The connect button. */
    private lateinit var btnConnect: JButton

    /** The controls button. */
    private lateinit var btnControls: JButton

    /** The camera button. */
    private lateinit var btnCamera: JButton

    /** The recorder button. */
    private lateinit var btnRecorder: JButton

    /** The AI button. */
    private lateinit var btnAi: JButton

    /** The status button. */
    private lateinit var lblStatus: JLabel

    /** The connected status. */
    private var isConnected = false

    /** The controlled status. */
    private var isControlled = false

    /** The streaming status. */
    private var isStreaming = false

    /** The recording status. */
    private var isRecording = false

    /** The AI status. */
    private var isAi = false

    init {
        init()
    }

    /**
     * Initializes the main window.
     */
    override fun init() {
        btnConnect.isEnabled = true
        btnControls.isEnabled = false
        btnCamera.isEnabled = false
        btnRecorder.isEnabled = false
        btnAi.isEnabled = false

        lblStatus.isEnabled = true

        btnConnect.addActionListener(this)
        btnControls.addActionListener(this)
        btnCamera.addActionListener(this)
        btnRecorder.addActionListener(this)
        btnAi.addActionListener(this)

        initModel()

        add(panel)
        title = cx10.name
        preferredSize = Dimension(WIDTH, HEIGHT)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        isAlwaysOnTop = true
        isLocationByPlatform = true
        panel.isVisible = true
        isVisible = true
        pack()

        println(ANSI.BLUE + "Welcome to the " + cx10.name + " Graphical User Interface (GUI)" + ANSI.RESET)
    }

    /**
     * Handles a performed action when a source is triggered.
     */
    override fun actionPerformed(actionEvent: ActionEvent) {
        thread { 
            when(actionEvent.source){
                btnConnect -> onConnectClicked()
                btnControls -> onControlsClicked()
                btnCamera -> onCameraClicked()
                btnRecorder -> onRecorderClicked()
                btnAi -> onAiClicked()
            }
        }
    }

    /**
     * Handles the connect button.
     */
    override fun onConnectClicked() {
        if (!isConnected) {
            connect()
        } else {
            disconnect()
        }
    }

    /**
     * Handles the controls button.
     */
    override fun onControlsClicked() {
        if (!isControlled) {
            startControls()
        } else {
            stopControls()
        }
    }

    /**
     * Handles the camera button.
     */
    override fun onCameraClicked() {
        if (!isStreaming) {
            startCamera()
        } else {
            stopCamera()
        }
    }

    /**
     * Handles the recorder button.
     */
    override fun onRecorderClicked() {
        if (!isRecording) {
            startRecorder()
        } else {
            stopRecorder()
        }
    }

    /**
     * Handles AI button.
     */
    override fun onAiClicked() {
        if (!isAi) {
            startAi()
        } else {
            stopAi()
        }
    }

    /**
     * Connects.
     */
    private fun connect() {
        try {
            println(ANSI.YELLOW + "Trying to establish a new connection..." + ANSI.RESET)

            updateModel{ 
                setBtnConnectContext(false, "Connecting...")
                setLblStatusContext(SpecialColor.YELLOW, "Trying to establish a new connection...")
            }

            cx10.connect()
            cx10.sendMessages()
            cx10.startHeartbeat()

            isConnected = true

            updateModel{
                setBtnConnectContext(true, "Disconnect")
                btnControlsEnabled = true
                btnCameraEnabled = true
                btnRecorderEnabled = true
                btnAiEnabled = true
                setLblStatusContext(SpecialColor.GREEN, "Connection successfully established")
            }

            println(ANSI.GREEN + "Connection successfully established" + ANSI.RESET)
        } catch (e: DroneException) {
            updateModel{ 
                setBtnConnectContext(true, "Connect")
                setLblStatusContext(SpecialColor.RED, "Connection failed!")
            }

            println(ANSI.RED + "Connection failed!" + ANSI.RESET)
        }

    }

    /**
     * Disconnects.
     */
    private fun disconnect() {
        try {
            println(ANSI.YELLOW + "Trying to disconnect..." + ANSI.RESET)

            updateModel{
                setBtnConnectContext(false, "Disconnecting...")
                setLblStatusContext(SpecialColor.YELLOW, "Trying to disconnect...")
            }

            stopRecorder()
            stopCamera()
            stopControls()
            stopAi()

            cx10.stopHeartbeat()
            cx10.disconnect()

            isConnected = false

            updateModel{
                setBtnConnectContext(true, "Connect")
                setBtnControlsContext(false, "Start Controls")
                setBtnCameraContext(false, "Start Camera")
                setBtnRecorderContext(false, "Start Recorder")
                setBtnAiContext(false, "Start AI")
                setLblStatusContext(SpecialColor.GREEN, "Disconnection successful")
            }

            println(ANSI.GREEN + "Disconnection successful" + ANSI.RESET)
        } catch (e: DroneException) {
            updateModel{
                setBtnConnectContext(true, "Disconnect")
                setLblStatusContext(SpecialColor.RED, "Disconnection failed!")
            }

            println(ANSI.RED + "Disconnection failed!" + ANSI.RESET)
        }

    }

    /**
     * Starts the controls.
     */
    private fun startControls() {
        try {
            println(ANSI.YELLOW + "Trying to start the controls..." + ANSI.RESET)

            updateModel{
                setBtnControlsContext(false, "Starting controls...")
                btnAiEnabled = false
                setLblStatusContext(SpecialColor.YELLOW, "Trying to start the controls...")
            }

            cx10.startControls(Keyboard(KeyboardFocusManager.getCurrentKeyboardFocusManager()))

            isControlled = true

            updateModel{
                setBtnControlsContext(true, "Stop Controls")
                setLblStatusContext(SpecialColor.GREEN, "Controls successfully started")
            }

            println(ANSI.GREEN + "Controls successfully started" + ANSI.RESET)
        } catch (e: IOException) {
            updateModel{
                setBtnControlsContext(true, "Start Controls")
                btnAiEnabled = true
                setLblStatusContext(SpecialColor.RED, "Starting the controls failed!")
            }
            
            println(ANSI.RED + "Starting the controls failed!" + ANSI.RESET)
        }

    }

    /**
     * Stops the controls.
     */
    private fun stopControls() {
        try {
            println(ANSI.YELLOW + "Trying to stop the controls..." + ANSI.RESET)

            updateModel{
                setBtnControlsContext(false, "Stopping controls...")
                setLblStatusContext(SpecialColor.YELLOW, "Trying to stop the controls...")
            }

            cx10.stopControls()

            isControlled = false

            updateModel{
                setBtnControlsContext(true, "Start Controls")
                btnAiEnabled = true
                setLblStatusContext(SpecialColor.GREEN, "Controls successfully stopped")
            }

            println(ANSI.GREEN + "Controls successfully stopped" + ANSI.RESET)
        } catch (e: DroneException) {
            updateModel{
                setBtnControlsContext(true, "Stop Controls")
                setLblStatusContext(SpecialColor.RED, "Stopping the controls failed!")
            }

            println(ANSI.RED + "Stopping the controls failed!" + ANSI.RESET)
        }

    }

    /**
     * Starts the camera.
     */
    private fun startCamera() {
        try {
            println(ANSI.YELLOW + "Trying to start the camera..." + ANSI.RESET)

            updateModel{
                setBtnCameraContext(false, "Starting camera...")
                btnRecorderEnabled = false
                setLblStatusContext(SpecialColor.YELLOW, "Trying to start the camera...")
            }

            cx10.startCamera()

            isStreaming = true

            updateModel{
                setBtnCameraContext(true, "Stop Camera")
                setLblStatusContext(SpecialColor.GREEN, "Camera successfully started")
            }

            println(ANSI.GREEN + "Camera successfully started" + ANSI.RESET)
        } catch (e: DroneException) {
            updateModel{
                setBtnCameraContext(true, "Start Camera")
                btnRecorderEnabled = true
                setLblStatusContext(SpecialColor.RED, "Starting the camera failed!")
            }

            println(ANSI.RED + "Starting the camera failed!" + ANSI.RESET)
        }

    }

    /**
     * Stops the camera.
     */
    private fun stopCamera() {
        try {
            println(ANSI.YELLOW + "Trying to stop the camera..." + ANSI.RESET)

            updateModel{
                setBtnCameraContext(false, "Stopping camera...")
                setLblStatusContext(SpecialColor.YELLOW, "Trying to stop the camera...")
            }

            cx10.stopCamera()

            isStreaming = false

            updateModel{
                setBtnCameraContext(true, "Start Camera")
                btnRecorderEnabled = true
                setLblStatusContext(SpecialColor.GREEN, "Camera successfully stopped")
            }

            println(ANSI.GREEN + "Camera successfully stopped" + ANSI.RESET)
        } catch (e: DroneException) {
            updateModel{
                setBtnCameraContext(true, "Stop Camera")
                setLblStatusContext(SpecialColor.RED, "Stopping the camera failed!")
            }

            println(ANSI.RED + "Stopping the camera failed!" + ANSI.RESET)
        }

    }

    /**
     * Starts the recorder.
     */
    private fun startRecorder() {
        try {
            println(ANSI.YELLOW + "Trying to start the recorder.." + ANSI.RESET)

            updateModel{
                btnCameraEnabled = false
                setBtnRecorderContext(false, "Starting recorder...")
                setLblStatusContext(SpecialColor.YELLOW, "Trying to start the recorder...")
            }

            cx10.startRecorder()

            isRecording = true

            updateModel{
                setBtnRecorderContext(true, "Stop Recorder")
                setLblStatusContext(SpecialColor.GREEN, "Recorder successfully started")
            }

            println(ANSI.GREEN + "Recorder successfully started" + ANSI.RESET)
        } catch (e: DroneException) {
            updateModel{
                btnCameraEnabled = true
                setBtnRecorderContext(true, "Start Recorder")
                setLblStatusContext(SpecialColor.RED, "Starting recording failed!")
            }

            println(ANSI.RED + "Starting recording failed!" + ANSI.RESET)
        }

    }

    /**
     * Stops the recorder.
     */
    private fun stopRecorder() {
        try {
            println(ANSI.YELLOW + "Trying to stop the recorder..." + ANSI.RESET)

            updateModel{
                setBtnRecorderContext(false, "Stopping recorder...")
                setLblStatusContext(SpecialColor.YELLOW, "Trying to stop the recorder.")
            }

            cx10.stopRecorder()

            isRecording = false

            updateModel{
                btnCameraEnabled = true
                setBtnRecorderContext(true, "Start Recorder")
                setLblStatusContext(SpecialColor.GREEN, "Recorder successfully stopped")
            }

            println(ANSI.GREEN + "Recorder successfully stopped" + ANSI.RESET)
        } catch (e: DroneException) {
            updateModel{
                setBtnRecorderContext(true, "Stop Recorder")
                setLblStatusContext(SpecialColor.RED, "Stopping the recorder failed!")
            }

            println(ANSI.RED + "Stopping the recorder failed!" + ANSI.RESET)
        }

    }

    /**
     * Starts the AI.
     */
    private fun startAi() {
        try {
            println(ANSI.YELLOW + "Trying to start the AI..." + ANSI.RESET)

            updateModel{
                btnControlsEnabled = false
                setBtnAiContext(false, "Starting AI...")
                setLblStatusContext(SpecialColor.YELLOW, "Trying to start AI...")
            }

            cx10.startAi()

            isAi = true

            updateModel{
                setBtnAiContext(true, "Stop AI")
                setLblStatusContext(SpecialColor.GREEN, "Starting AI successfully started")
            }

            println(ANSI.GREEN + "Starting AI successfully started" + ANSI.RESET)
        } catch (e: DroneException) {
            updateModel{
                btnControlsEnabled = true
                setBtnAiContext(true, "Start AI")
                setLblStatusContext(SpecialColor.RED, "Starting AI failed!")
            }

            println(ANSI.RED + "Starting AI failed!" + ANSI.RESET)
        }

    }

    /**
     * Stops the AI.
     */
    private fun stopAi() {
        try {
            println(ANSI.YELLOW + "Trying to stop the AI..." + ANSI.RESET)

            updateModel{
                setBtnAiContext(false, "Stopping AI...")
                setLblStatusContext(SpecialColor.YELLOW, "Trying to stop the AI.")
            }

            cx10.stopAi()

            isAi = false

            updateModel {
                btnControlsEnabled = true
                setBtnAiContext(true, "Start AI")
                setLblStatusContext(SpecialColor.GREEN, "AI successfully stopped")
            }

            println(ANSI.GREEN + "AI successfully stopped" + ANSI.RESET)
        } catch (e: DroneException) {
            updateModel{
                setBtnAiContext(true, "Stop AI")
                setLblStatusContext(SpecialColor.RED, "Stopping the AI failed!")
            }

            println(ANSI.RED + "Stopping the AI failed!" + ANSI.RESET)
        }

    }

    /**
     * Initializes the default model.
     */
    private fun initModel() = MainWindowModel(
        btnConnectEnabled = btnConnect.isEnabled,
        btnConnectText = btnConnect.text,

        btnControlsEnabled = btnControls.isEnabled,
        btnControlsText = btnControls.text,

        btnCameraEnabled = btnCamera.isEnabled,
        btnCameraText = btnCamera.text,

        btnRecorderEnabled = btnRecorder.isEnabled,
        btnRecorderText = btnRecorder.text,

        btnAiEnabled = btnAi.isEnabled,
        btnAiText = btnAi.text,

        lblStatusEnabled = lblStatus.isEnabled,
        lblStatusText = lblStatus.text,
        lblStatusForeground = lblStatus.foreground
    )

    /**
     * Updates the model.

     * @param modelConsumer the model consumer
     */
    private fun updateModel(modelConsumer: MainWindowModel.()->Unit) {
        val model = this.model.copy()
        modelConsumer(model)
        updateModel(model)
        this.model = model
    }

    /**
     * Updates the model to the view.

     * @param model the model
     */
    private fun updateModel(model: MainWindowModel) {
        SwingUtilities.invokeLater {
            btnConnect.isEnabled = model.btnConnectEnabled
            btnConnect.text = model.btnConnectText

            btnControls.isEnabled = model.btnControlsEnabled
            btnControls.text = model.btnControlsText

            btnCamera.isEnabled = model.btnCameraEnabled
            btnCamera.text = model.btnCameraText

            btnRecorder.isEnabled = model.btnRecorderEnabled
            btnRecorder.text = model.btnRecorderText

            btnAi.isEnabled = model.btnAiEnabled
            btnAi.text = model.btnAiText

            lblStatus.isEnabled = model.lblStatusEnabled
            lblStatus.text = model.lblStatusText
            lblStatus.foreground = model.lblStatusForeground
        }
    }

    companion object {

        /**
         * The width of the main window.
         */
        private val WIDTH = 750

        /**
         * The height of the main window.
         */
        private val HEIGHT = 150
    }

}
