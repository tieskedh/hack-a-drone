package nl.ordina.jtech.hackadrone

import nl.ordina.jtech.hackadrone.io.Device
import nl.ordina.jtech.hackadrone.net.Connection

/**
 * Interface representing a drone.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 */
interface Drone : Connection {

    /**
     * Connects.

     * @throws DroneException if the connection failed
     */
    @Throws(DroneException::class)
    override fun connect()

    /**
     * Disconnects.

     * @throws DroneException if the disconnection failed
     */
    @Throws(DroneException::class)
    override fun disconnect()

    /**
     * Sends messages.

     * @throws DroneException if sending the messages failed
     */
    @Throws(DroneException::class)
    fun sendMessages()

    /**
     * Starts the heartbeat.

     * @throws DroneException if starting the heartbeat failed
     */
    @Throws(DroneException::class)
    fun startHeartbeat()

    /**
     * Stops the heartbeat.

     * @throws DroneException if stopping the heartbeat failed
     */
    @Throws(DroneException::class)
    fun stopHeartbeat()

    /**
     * Starts the controls.

     * @param device the device to use the controls from
     * *
     * @throws DroneException if starting the controls failed
     */
    @Throws(DroneException::class)
    fun startControls(device: Device)

    /**
     * Stops the controls.

     * @throws DroneException if stopping the controls failed
     */
    @Throws(DroneException::class)
    fun stopControls()

    /**
     * Starts the camera.

     * @throws DroneException if starting the camera failed
     */
    @Throws(DroneException::class)
    fun startCamera()

    /**
     * Stops the camera.

     * @throws DroneException if stopping the camera failed
     */
    @Throws(DroneException::class)
    fun stopCamera()

    /**
     * Starts the recorder.

     * @throws DroneException if starting the recorder failed
     */
    @Throws(DroneException::class)
    fun startRecorder()

    /**
     * Stops the recorder.

     * @throws DroneException if stopping the recorder failed
     */
    @Throws(DroneException::class)
    fun stopRecorder()

    /**
     * Starts the AI.

     * @throws DroneException if starting the AI failed
     */
    @Throws(DroneException::class)
    fun startAi()

    /**
     * Stops the AI.

     * @throws DroneException if stopping the AI failed
     */
    @Throws(DroneException::class)
    fun stopAi()

    /**
     * Gets the name of the drone.

     * @return the name of the drone
     */
    val name: String

}
