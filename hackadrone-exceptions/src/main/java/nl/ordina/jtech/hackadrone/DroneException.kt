package nl.ordina.jtech.hackadrone

import java.io.IOException

/**
 * Class representing a drone exception.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 *
 * @param message the message that will be given by the exception
 */
class DroneException(message: String) : IOException(message)
