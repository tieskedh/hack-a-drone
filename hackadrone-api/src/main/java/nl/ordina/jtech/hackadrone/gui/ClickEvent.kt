package nl.ordina.jtech.hackadrone.gui

/**
 * Interface representing a click event.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 */
interface ClickEvent {

    /**
     * Handles the connect button.
     */
    fun onConnectClicked()

    /**
     * Handles the controls button.
     */
    fun onControlsClicked()

    /**
     * Handles the camera button.
     */
    fun onCameraClicked()

    /**
     * Handles the recorder button.
     */
    fun onRecorderClicked()

    /**
     * Handles AI button.
     */
    fun onAiClicked()

}
