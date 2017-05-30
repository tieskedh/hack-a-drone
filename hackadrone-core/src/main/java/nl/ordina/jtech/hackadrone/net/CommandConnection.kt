package nl.ordina.jtech.hackadrone.net

import nl.ordina.jtech.hackadrone.models.Command
import nl.ordina.jtech.hackadrone.utils.ByteUtils

import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import kotlin.experimental.xor

class CommandConnection @Throws(IOException::class)
constructor(host: String, private val port: Int) : Execute {

    private val socket = DatagramSocket()
    private val host: InetAddress = InetAddress.getByName(host)

    @Throws(IOException::class)
    override fun sendCommand(command: Command) {
        val data = command.toByteArray()
        val packet = DatagramPacket(data, 0, data.size, host, port)
        socket.send(packet)
    }


    private fun Command.toByteArray(): ByteArray {
        operator fun ByteArray.set(index: Int, value: Int) {
            this[index] = value.toByte()
        }

        val data = ByteArray(8)
        data[0] = 0xCC
        data[1] = roll + 128
        data[2] = pitch + 128
        data[3] = throttle + 128
        data[4] = yaw + 128

        data[5] = if (takeOff) {
            0x01
        } else if (land) {
            0x02
        } else {
            0x00
        }

        data[6] = checksum(ByteUtils.asUnsigned(*data.sliceArray(1..5).map { it.toInt() }.toIntArray()))
        data[7] = 0x33

        return data
    }

    private fun checksum(bytes: ByteArray) = bytes.fold<Byte>(0) {
        first, second -> first xor second
    }
}
