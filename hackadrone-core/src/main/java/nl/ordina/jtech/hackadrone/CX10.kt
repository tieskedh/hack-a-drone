package nl.ordina.jtech.hackadrone

import nl.ordina.jtech.hackadrone.io.*
import nl.ordina.jtech.hackadrone.net.CommandConnection
import nl.ordina.jtech.hackadrone.net.TransportConnection
import nl.ordina.jtech.hackadrone.utils.ByteUtils

import java.io.IOException

/**
 * Class representing a CX10 drone.
 *
 * @author Nils Berlijn
 * @version 1.0
 * @since 1.0
 */
class CX10 : Drone {
    /** The transacrion connection with the host */
    private val transportConnection: TransportConnection by lazy { TransportConnection(DRONE_HOST, DRONE_PORT) }
    /** The controller */
    private var controller: Controller? = null
    /** The heartbeat */
    private var heartbeat: Heartbeat? = null
    /** The camera-handler */
    private var camera: Handler? = null
    /** The recorder-handler */
    private var recorder: Handler? = null
    /** The AI-controller */
    private var ai: Controller? = null

    /**
     * Connects to the drone
     *
     * @throws DroneException if the connection fails
     */
    @Throws(DroneException::class)
    override fun connect() {
        try {
            transportConnection.connect()
        } catch (e: IOException) {
            throw DroneException("Connection with the $NAME failed!")
        }
    }

    /**
     * Disconnects to the drone
     *
     * @throws DroneException if the disconnection fails
     */
    @Throws(DroneException::class)
    override fun disconnect() {
        try {
            transportConnection.disconnect()
        } catch (e: IOException) {
            throw DroneException("Disconnecting with the $NAME failed!")
        } catch (e: NullPointerException) {
            throw DroneException("Disconnecting with the $NAME failed!")
        }

    }

    /**
     * Send messages
     *
     * @throws DroneException if sending the messages failed
     */
    @Throws(DroneException::class)
    override fun sendMessages() {
        try {
            transportConnection.sendMessage(ByteUtils.loadMessageFromFile("bin/message1.bin"), 106)
            transportConnection.sendMessage(ByteUtils.loadMessageFromFile("bin/message2.bin"), 106)
            transportConnection.sendMessage(ByteUtils.loadMessageFromFile("bin/message3.bin"), 170)
            transportConnection.sendMessage(ByteUtils.loadMessageFromFile("bin/message4.bin"), 106)
            transportConnection.sendMessage(ByteUtils.loadMessageFromFile("bin/message5.bin"), 106)
        } catch (e: IOException) {
            throw DroneException("Sending messages to the $NAME failed!")
        }
    }

    /**
     * Starts the heartbeat.
     *
     * @throws DroneException if starting the heartbeat failed
     */
    @Throws(DroneException::class)
    override fun startHeartbeat() {
        if (heartbeat == null) {
            heartbeat = Heartbeat(DRONE_HOST, DRONE_PORT).apply { start() }
        } else {
            throw DroneException("Starting the heartbeat of the $NAME failed!")
        }
    }

    /**
     * Stops the heartbeat.
     *
     * @throws DroneException if stopping the heartbeat failed
     */
    @Throws(DroneException::class)
    override fun stopHeartbeat() {
        if (heartbeat != null) {
            heartbeat!!.interrupt()
            heartbeat = null
        } else {
            throw DroneException("Stopping the heartbeat of the $NAME failed!")
        }
    }

    /**
     * Starts the controls.
     *
     * @param device the device to use the controls from
     * @throws DroneException if starting the controls failed
     */
    @Throws(DroneException::class)
    override fun startControls(device: Device) {
        try {
            controller = (controller?: Controller(device, CommandConnection(IO_HOST, IO_PORT))).apply { start() }
        } catch (e: IOException) {
            throw DroneException("Starting the controls of the $NAME failed!")
        } catch (e: IllegalThreadStateException) {
            throw DroneException("Starting the controls of the $NAME failed!")
        }

    }

    /**
     * Stops the controls.
     *
     * @throws DroneException if stopping the controls failed
     */
    @Throws(DroneException::class)
    override fun stopControls() {
        if (controller != null) {
            controller!!.interrupt()
            controller = null
        } else {
            throw DroneException("Stopping the controls of the $NAME failed!")
        }
    }

    /**
     * Starts the camera.
     *
     * @throws DroneException if starting the camera failed
     */
    @Throws(DroneException::class)
    override fun startCamera() {
        if (camera == null) {
            camera = Camera(DRONE_HOST, DRONE_PORT, CAMERA_HOST, CAMERA_PORT).apply {
                start()
            }
        } else {
            throw DroneException("Starting the camera of the $NAME failed!")
        }
    }

    /**
     * Stops the camera.
     *
     * @throws DroneException if stopping the camera failed
     */
    @Throws(DroneException::class)
    override fun stopCamera() {
        if (camera != null) {
            camera!!.stop()
            camera = null
        } else {
            throw DroneException("Stopping the camera of the $NAME failed!")
        }
    }

    /**
     * Starts the recorder.
     *
     * @throws DroneException if starting the recorder failed
     */
    @Throws(DroneException::class)
    override fun startRecorder() {
        if (recorder == null) {
            recorder = Recorder(DRONE_HOST, DRONE_PORT, RECORDER_HOST, RECORDER_PORT).apply {
                start()
            }
        } else {
            throw DroneException("Starting the recorder of the $NAME failed!")
        }
    }

    /**
     * Stops the recorder.
     *
     * @throws DroneException if stopping the recorder failed
     */
    @Throws(DroneException::class)
    override fun stopRecorder() {
        if (recorder != null) {
            recorder!!.stop()
            recorder = null
        } else {
            throw DroneException("Stopping the recorder of the $NAME failed!")
        }
    }

    /**
     * Starts the AI.
     *
     * @throws DroneException if starting the AI failed
     */
    @Throws(DroneException::class)
    override fun startAi() {
        try {
            ai = (ai?: Controller(AI(), CommandConnection(IO_HOST, IO_PORT))).apply {
                start()
            }
        } catch (e: IOException) {
            throw DroneException("Starting the AI of the $NAME failed!")
        } catch (e: IllegalThreadStateException) {
            throw DroneException("Starting the AI of the $NAME failed!")
        }
    }

    /**
     * Stops the AI.
     *
     * @throws DroneException if stopping the AI failed
     */
    @Throws(DroneException::class)
    override fun stopAi() {
        if (ai != null) {
            ai!!.interrupt()
            ai = null
        } else {
            throw DroneException("Stopping AI of the $NAME failed!")
        }
    }

    /**
     * Gets the name of the drone.
     *
     * @return the name of the drone
     */
    override val name get() = NAME

    companion object {
        /** the name of the drone */
        private val NAME = "Cheerson CX-10WD-TX Mini FPV Drone"

        /** the host of the drone */
        private val DRONE_HOST = "172.16.10.1"
        /**The port of the drone */
        private val DRONE_PORT = 8888

        /** The host of the IO */
        private val IO_HOST = "172.16.10.1"
        /** The port of the IO */
        private val IO_PORT = 8895

        /** The host of the Camera */
        private val CAMERA_HOST = "127.0.0.1"
        /** The port of the Camera */
        private val CAMERA_PORT = 8889

        /** The host of the recorder */
        private val RECORDER_HOST = "127.0.0.1"
        /** The port of the recorder */
        private val RECORDER_PORT = 8890
    }

}
