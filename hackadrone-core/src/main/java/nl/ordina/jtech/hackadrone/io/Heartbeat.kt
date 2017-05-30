package nl.ordina.jtech.hackadrone.io

import nl.ordina.jtech.hackadrone.utils.ByteUtils

import java.io.*
import java.net.Socket

class Heartbeat(private val host: String, private val port: Int) : Thread() {
    private lateinit var socket: Socket

    @Synchronized override fun start() {
        try {
            socket = Socket(host, port)
            super.start()
        } catch (e: IOException) {
            System.err.println("Connection failed!")
        }

    }

    override fun run() {
        while (!isInterrupted) {
            try {
                sendHeartBeat()
                Thread.sleep(5000)
            } catch (e: IOException) {
                System.err.println("Unable to send heartbeat")
            } catch (e: InterruptedException) {
                System.err.println("Heartbeat interrupted")
            }

        }
    }

    @Throws(IOException::class)
    private fun sendHeartBeat() {
        val heartbeatData = ByteUtils.loadMessageFromFile("bin/heartbeat.bin")
        val start = 0
        val length = heartbeatData.size

        val outputStream = socket.getOutputStream()
        val dataOutputStream = DataOutputStream(outputStream)

        if (length > 0) {
            dataOutputStream.write(heartbeatData, start, length)
        }

        dataOutputStream.flush()

        val dataInputStream = DataInputStream(socket.getInputStream())
        val byteArrayOutputStream = ByteArrayOutputStream()

        val buf = ByteArray(106)
        val bytesRead = dataInputStream.read(buf)

        byteArrayOutputStream.write(buf, 0, bytesRead)
    }

}
