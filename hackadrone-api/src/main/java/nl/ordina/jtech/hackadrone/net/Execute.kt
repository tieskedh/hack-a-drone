package nl.ordina.jtech.hackadrone.net

import nl.ordina.jtech.hackadrone.models.Command

import java.io.IOException

/**
 * Interface representing an execute.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 */
interface Execute {

    /**
     * Sends a command.

     * @param command the command to send
     * *
     * @throws IOException if sending the command failed
     */
    @Throws(IOException::class)
    fun sendCommand(command: Command)

}
