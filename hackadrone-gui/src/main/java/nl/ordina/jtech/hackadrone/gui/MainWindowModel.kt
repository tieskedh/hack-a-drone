package nl.ordina.jtech.hackadrone.gui

import java.awt.*

/**
 * Class representing a main window model for a main window.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 *
 * @param btnConnectEnabled the status of the connect button
 *
 * @param btnConnectText the text of the connect button
 *
 * @param btnControlsEnabled the status of the controls button
 *
 * @param btnControlsText the text of the controls button
 *
 * @param btnCameraEnabled the status of the camera button
 *
 * @param btnCameraText the text of the camera button
 *
 * @param btnRecorderEnabled the status of the recorder button
 *
 * @param btnRecorderText the text of the recorder button
 *
 * @param btnAiEnabled the status of the AI button
 *
 * @param btnAiText the text of the AI button
 *
 * @param lblStatusEnabled the status of the status label
 *
 * @param lblStatusForeground the foreground color of the status label
 *
 * @param lblStatusText the text of the status label
 *
 */
internal data class MainWindowModel(
    var btnConnectEnabled: Boolean,
    var btnConnectText: String,
    var btnControlsEnabled: Boolean,
    var btnControlsText: String,
    var btnCameraEnabled: Boolean,
    var btnCameraText: String,
    var btnRecorderEnabled: Boolean,
    var btnRecorderText: String,
    var btnAiEnabled: Boolean,
    var btnAiText: String,
    var lblStatusEnabled: Boolean,
    var lblStatusForeground: Color,
    var lblStatusText: String){


    /**
     * Sets the context of the connect button

     * @param btnConnectEnabled the status of the connect button
     * *
     * @param btnConnectText the text of the connect buttion
     */
    fun setBtnConnectContext(btnConnectEnabled: Boolean, btnConnectText: String) {
        this.btnConnectEnabled = btnConnectEnabled
        this.btnConnectText = btnConnectText
    }

    /**
     * Sets the context of the controls button

     * @param btnControlsEnabled the status of the controls button
     * *
     * @param btnControlsText the text of the controls button
     */
    fun setBtnControlsContext(btnControlsEnabled: Boolean, btnControlsText: String) {
        this.btnControlsEnabled = btnControlsEnabled
        this.btnControlsText = btnControlsText
    }

    /**
     * Sets the context of the camera button.

     * @param btnCameraEnabled the status of the camera button
     * *
     * @param btnCameraText the text of the camera button
     */
    fun setBtnCameraContext(btnCameraEnabled: Boolean, btnCameraText: String) {
        this.btnCameraEnabled = btnCameraEnabled
        this.btnCameraText = btnCameraText
    }

    /**
     * Sets the context of the recorder button.

     * @param btnRecorderEnabled the status of the recorder button
     * *
     * @param btnRecorderText the text of the recorder button
     */
    fun setBtnRecorderContext(btnRecorderEnabled: Boolean, btnRecorderText: String) {
        this.btnRecorderEnabled = btnRecorderEnabled
        this.btnRecorderText = btnRecorderText
    }

    /**
     * Sets the context of the AI button.

     * @param btnAiEnabled the status of the AI button
     * *
     * @param btnAiText the text of the AI button
     */
    fun setBtnAiContext(btnAiEnabled: Boolean, btnAiText: String) {
        this.btnAiEnabled = btnAiEnabled
        this.btnAiText = btnAiText
    }

    /**
     * Sets the context of the status label.

     * @param lblStatusForeground the status of the status label
     * *
     * @param lblStatusText the text of the status label
     */
    fun setLblStatusContext(lblStatusForeground: Color, lblStatusText: String) {
        this.lblStatusForeground = lblStatusForeground
        this.lblStatusText = lblStatusText
    }
}
