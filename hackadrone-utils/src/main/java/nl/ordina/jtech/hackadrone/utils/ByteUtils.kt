package nl.ordina.jtech.hackadrone.utils

import java.io.IOException

object ByteUtils {

    fun asUnsigned(vararg values: Byte): ByteArray {
        val intArray = values.toTypedArray().map { it.toInt() }.toIntArray()
        return asUnsigned(*intArray)
    }

    fun asUnsigned(vararg values: Int): ByteArray {
        val bytes = ByteArray(values.size)

        for (i in values.indices) {
            val value = values[i]

            if (value > Byte.MAX_VALUE) {
                bytes[i] = value.toByte()
            } else {
                bytes[i] = (value and 0xff).toByte()
            }
        }

        return bytes
    }

    @Throws(IOException::class)
    fun loadMessageFromFile(fileName: String): ByteArray {
        return ByteUtils::class.java
                .getResourceAsStream("/$fileName")
                .use { it.readBytes() }
    }
}