package nl.ordina.jtech.hackadrone.io

import nl.ordina.jtech.hackadrone.net.Decoder
import nl.ordina.jtech.hackadrone.net.DroneDecoder
import java.io.BufferedOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.InetAddress
import java.net.Socket
import kotlin.concurrent.thread

abstract class ScreenCapturer(
        private val droneHost: String,
        private val dronePort: Int,
        private val videoHost: String,
        private val videoPort: Int,
        private val action: String
): Handler{
    private var process: Process? = null
    private var socket: Socket? = null
    private var outputStream: OutputStream? = null
    private var decoder: Decoder? = null

    protected val output get() = "tcp://$videoHost:$videoPort?listen"
    override fun start() {
        try {
            process?.run {
                stop()
            }

            process = getProcess()

            Thread.sleep(1000)

            socket = Socket(InetAddress.getByName(videoHost), videoPort).also {
                outputStream = BufferedOutputStream(it.getOutputStream())
            }

            startProcess()
        } catch (e: IOException) {
            println("Unable to start $action")
        } catch (e: InterruptedException) {
            println("Unable to start $action")
        }
    }

    override fun stop() {
        process?.destroy()
        process = null

        try {
            outputStream?.close()
            socket?.close()
        } catch (e: IOException){
            println("Unable to stop $action")
        }
        outputStream = null
        socket = null
    }


    @Throws(IOException::class)
    abstract protected fun getProcess() : Process

    @Throws(IOException::class)
    private fun startProcess(){
        decoder?.run {
            throw IOException("Starting $action failed!")
        }

        decoder = DroneDecoder(droneHost, dronePort).apply {
            connect()
        }

        thread {
            var data: ByteArray? = null
            do {
                try {
                    data = decoder!!.read()

                    outputStream?.write(data)
                    if (outputStream == null) {
                        decoder!!.disconnect()
                        break
                    }
                } catch (e: IOException) {
                    System.err.println("Unable to read $action output stream")
                }
            }while (data != null)
            decoder = null
        }
    }

    companion object {
        @JvmStatic protected val VIDEO_PATH = System.getProperty("user.dir") + "/hackadrone-persistence/target/classes/video"
    }
}