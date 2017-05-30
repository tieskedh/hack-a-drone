package nl.ordina.jtech.hackadrone.io

/**
 * Interface representing a device.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 */
interface Device : Handler {

    /**
     * Sets the listener.

     * @param commandListener the command listener to set
     */
    fun setListener(commandListener: CommandListener?)

}
