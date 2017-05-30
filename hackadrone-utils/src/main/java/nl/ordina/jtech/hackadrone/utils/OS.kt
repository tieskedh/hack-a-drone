package nl.ordina.jtech.hackadrone.utils

/**
 * Class representing OS utils.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 */
object OS {

    /**
     * Gets the used OS.

     * @return the used os
     */
    val os: String by lazy {
            var OS = System.getProperty("os.name").toLowerCase()

            if ("win" in OS) {
                OS = "win"
            } else if ("nix" in OS || "nux" in OS || "aix" in OS) {
                OS = "unix"
            } else if ("osx" in OS || "max" in OS) {
                OS = "osx"
            }
            return@lazy OS
        }
}