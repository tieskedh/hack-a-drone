package nl.ordina.jtech.hackadrone.net

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.InetAddress
import java.net.Socket

class TransportConnection(private val host: String, private val port: Int) : Connection, Transport {
    private var socket: Socket? = null

    @Throws(IOException::class)
    override fun connect() {
        if (socket == null || socket!!.isClosed) {
            socket = Socket(InetAddress.getByName(host), port)
        } else {
            throw IOException("Connection failed!")
        }
    }

    @Throws(IOException::class)
    override fun disconnect() {
        if (socket != null && !socket!!.isClosed) {
            socket!!.close()
        } else {
            throw IOException("Disconnection failed!")
        }
    }

    @Throws(IOException::class)
    override fun sendMessage(bytes: ByteArray, responseSize: Int) {
        val dataOutputStream = DataOutputStream(socket!!.getOutputStream())
        dataOutputStream.write(bytes)

        val buffer = ByteArray(responseSize)

        val dataInputStream = DataInputStream(socket!!.getInputStream())
        dataInputStream.readFully(buffer)
    }

}
