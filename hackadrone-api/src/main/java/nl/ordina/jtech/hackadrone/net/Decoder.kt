package nl.ordina.jtech.hackadrone.net

import java.io.IOException

/**
 * Interface representing a decoder.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 */
interface Decoder : Connection {

    /**
     * Reads.

     * @return the read bytes
     * *
     * @throws IOException if reading failed
     */
    @Throws(IOException::class)
    fun read(): ByteArray

}
