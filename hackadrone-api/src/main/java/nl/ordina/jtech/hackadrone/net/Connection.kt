package nl.ordina.jtech.hackadrone.net

import java.io.IOException

/**
 * Interface representing a connection.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 */
interface Connection {

    /**
     * Connects.

     * @throws IOException if the connection failed
     */
    @Throws(IOException::class)
    fun connect()

    /**
     * Disconnects.

     * @throws IOException if the disconnection failed
     */
    @Throws(IOException::class)
    fun disconnect()

}
