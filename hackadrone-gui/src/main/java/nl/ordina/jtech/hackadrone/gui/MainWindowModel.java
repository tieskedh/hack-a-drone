package nl.ordina.jtech.hackadrone.gui;

import java.awt.*;

/**
 * Class representing a main window model for a main window.
 *
 * @author Nils Berlijn
 * @version 1.0
 * @since 1.0
 */
class MainWindowModel {

    /**
     * The status of the connect button.
     */
    private boolean btnConnectEnabled;

    /**
     * The text of the connect button.
     */
    private String btnConnectText;

    /**
     * The status of the controls button.
     */
    private boolean btnControlsEnabled;

    /**
     * The text of the controls button.
     */
    private String btnControlsText;

    /**
     * The status of the camera button.
     */
    private boolean btnCameraEnabled;

    /**
     * The text of the camera button.
     */
    private String btnCameraText;

    /**
     * The status of the recorder button.
     */
    private boolean btnRecorderEnabled;

    /**
     * The text of the recorder button.
     */
    private String btnRecorderText;

    /**
     * The status of the AI button.
     */
    private boolean btnAiEnabled;

    /**
     * The text of the AI button.
     */
    private String btnAiText;

    /**
     * The status of the status label.
     */
    private boolean lblStatusEnabled;

    /**
     * The foreground color of the status label.
     */
    private Color lblStatusForeground;

    /**
     * The text of the status label.
     */
    private String lblStatusText;

    /**
     * A main window model constructor.
     */
    MainWindowModel() {

    }

    /**
     * A main window model constructor.
     *
     * @param btnConnectEnabled the status of the connect button
     * @param btnConnectText the text of the connect button
     * @param btnControlsEnabled the status of the controls button
     * @param btnControlsText the text of the controls button
     * @param btnCameraEnabled the status of the camera button
     * @param btnCameraText the text of the camera button
     * @param btnRecorderEnabled the status of the recorder button
     * @param btnRecorderText the text of the recorder button
     * @param btnAiEnabled the status of the AI button
     * @param btnAiText the text of the AI button
     * @param lblStatusEnabled the status of the status label
     * @param lblStatusForeground the foreground color of the status label
     * @param lblStatusText the text of the status label
     */
    private MainWindowModel(boolean btnConnectEnabled, String btnConnectText, boolean btnControlsEnabled, String btnControlsText, boolean btnCameraEnabled, String btnCameraText, boolean btnRecorderEnabled, String btnRecorderText, boolean btnAiEnabled, String btnAiText, boolean lblStatusEnabled, Color lblStatusForeground, String lblStatusText) {
        this.btnConnectEnabled = btnConnectEnabled;
        this.btnConnectText = btnConnectText;
        this.btnControlsEnabled = btnControlsEnabled;
        this.btnControlsText = btnControlsText;
        this.btnCameraEnabled = btnCameraEnabled;
        this.btnCameraText = btnCameraText;
        this.btnRecorderEnabled = btnRecorderEnabled;
        this.btnRecorderText = btnRecorderText;
        this.btnAiEnabled = btnAiEnabled;
        this.btnAiText = btnAiText;
        this.lblStatusEnabled = lblStatusEnabled;
        this.lblStatusForeground = lblStatusForeground;
        this.lblStatusText = lblStatusText;
    }

    /**
     * Gets the status of the connect button.
     *
     * @return the status of the connect button
     */
    boolean isBtnConnectEnabled() {
        return btnConnectEnabled;
    }

    /**
     * Sets the status of the connect button.
     *
     * @param btnConnectEnabled the status of the connect button
     */
    void setBtnConnectEnabled(boolean btnConnectEnabled) {
        this.btnConnectEnabled = btnConnectEnabled;
    }

    /**
     * Gets the text of the connect button.
     *
     * @return the text of the connect button
     */
    String getBtnConnectText() {
        return btnConnectText;
    }

    /**
     * Sets the text of the connect button.
     *
     * @param btnConnectText the text of the connect button
     */
    void setBtnConnectText(String btnConnectText) {
        this.btnConnectText = btnConnectText;
    }

    /**
     * Sets the context of the connect button
     *
     * @param btnConnectEnabled the status of the connect button
     * @param btnConnectText the text of the connect buttion
     */
    void setBtnConnectContext(boolean btnConnectEnabled, String btnConnectText) {
        this.btnConnectEnabled = btnConnectEnabled;
        this.btnConnectText = btnConnectText;
    }

    /**
     * Gets the status of the controls button.
     *
     * @return the status of the controls button
     */
    boolean isBtnControlsEnabled() {
        return btnControlsEnabled;
    }

    /**
     * Sets the status of the controls button.
     *
     * @param btnControlsEnabled the status of the controls button
     */
    void setBtnControlsEnabled(boolean btnControlsEnabled) {
        this.btnControlsEnabled = btnControlsEnabled;
    }

    /**
     * Gets the text of the controls button.
     *
     * @return the text of the controls button
     */
    String getBtnControlsText() {
        return btnControlsText;
    }

    /**
     * Sets the text of the controls button.
     *
     * @param btnControlsText the text of the controls button
     */
    void setBtnControlsText(String btnControlsText) {
        this.btnControlsText = btnControlsText;
    }

    /**
     * Sets the context of the controls button
     *
     * @param btnControlsEnabled the status of the controls button
     * @param btnControlsText the text of the controls button
     */
    void setBtnControlsContext(boolean btnControlsEnabled, String btnControlsText) {
        this.btnControlsEnabled = btnControlsEnabled;
        this.btnControlsText = btnControlsText;
    }

    /**
     * Gets the status of the camera button.
     *
     * @return the status of the camera button
     */
    boolean isBtnCameraEnabled() {
        return btnCameraEnabled;
    }

    /**
     * Sets the status of the camera button.
     *
     * @param btnCameraEnabled the status of the camera button
     */
    void setBtnCameraEnabled(boolean btnCameraEnabled) {
        this.btnCameraEnabled = btnCameraEnabled;
    }

    /**
     * Gets the text of the camera button.
     *
     * @return the text of the camera button
     */
    String getBtnCameraText() {
        return btnCameraText;
    }

    /**
     * Sets the text of the camera button.
     *
     * @param btnCameraText the text of the camera button
     */
    void setBtnCameraText(String btnCameraText) {
        this.btnCameraText = btnCameraText;
    }

    /**
     * Sets the context of the camera button.
     *
     * @param btnCameraEnabled the status of the camera button
     * @param btnCameraText the text of the camera button
     */
    void setBtnCameraContext(boolean btnCameraEnabled, String btnCameraText) {
        this.btnCameraEnabled = btnCameraEnabled;
        this.btnCameraText = btnCameraText;
    }

    /**
     * Gets the status of the recorder button.
     *
     * @return the status of the recorder button
     */
    boolean isBtnRecorderEnabled() {
        return btnRecorderEnabled;
    }

    /**
     * Sets the status of the recorder button.
     *
     * @param btnRecorderEnabled the status of the recorder button.
     */
    void setBtnRecorderEnabled(boolean btnRecorderEnabled) {
        this.btnRecorderEnabled = btnRecorderEnabled;
    }

    /**
     * Gets the text of the recorder button.
     *
     * @return the text of the recorder button.
     */
    String getBtnRecorderText() {
        return btnRecorderText;
    }

    /**
     * Sets the text of the recorder button.
     *
     * @param btnRecorderText the text of the recorder text.
     */
    void setBtnRecorderText(String btnRecorderText) {
        this.btnRecorderText = btnRecorderText;
    }

    /**
     * Sets the context of the recorder button.
     *
     * @param btnRecorderEnabled the status of the recorder button
     * @param btnRecorderText the text of the recorder button
     */
    void setBtnRecorderContext(boolean btnRecorderEnabled, String btnRecorderText) {
        this.btnRecorderEnabled = btnRecorderEnabled;
        this.btnRecorderText = btnRecorderText;
    }

    /**
     * Gets the status of the AI button.
     *
     * @return the status of the AI button
     */
    boolean isBtnAiEnabled() {
        return btnAiEnabled;
    }

    /**
     * Sets the status of the AI button.
     *
     * @param btnAiEnabled the status of the AI button.
     */
    void setBtnAiEnabled(boolean btnAiEnabled) {
        this.btnAiEnabled = btnAiEnabled;
    }

    /**
     * Gets the text of the AI button.
     *
     * @return the text of the AI button
     */
    String getBtnAiText() {
        return btnAiText;
    }

    /**
     * Sets the text of the AI button.
     *
     * @param btnAiText the text of the AI button
     */
    void setBtnAiText(String btnAiText) {
        this.btnAiText = btnAiText;
    }

    /**
     * Sets the context of the AI button.
     *
     * @param btnAiEnabled the status of the AI button
     * @param btnAiText the text of the AI button
     */
    void setBtnAiContext(boolean btnAiEnabled, String btnAiText) {
        this.btnAiEnabled = btnAiEnabled;
        this.btnAiText = btnAiText;
    }

    /**
     * Gets the status of the status label.
     *
     * @return the status of the status label
     */
    boolean isLblStatusEnabled() {
        return lblStatusEnabled;
    }

    /**
     * Sets the status of the status label.
     *
     * @param lblStatusEnabled the status of the status label
     */
    void setLblStatusEnabled(boolean lblStatusEnabled) {
        this.lblStatusEnabled = lblStatusEnabled;
    }

    /**
     * Sets the foreground color of the status label.
     *
     * @return the foreground color of the status label
     */
    Color getLblStatusForeground() {
        return lblStatusForeground;
    }

    /**
     * Sets the the foreground color of the status label.
     *
     * @param lblStatusForeground the foreground color of the status label
     */
    void setLblStatusForeground(Color lblStatusForeground) {
        this.lblStatusForeground = lblStatusForeground;
    }

    /**
     * Gets the text of the status label.
     *
     * @return the text of the status label
     */
    String getLblStatusText() {
        return lblStatusText;
    }

    /**
     * Sets the text of the status label.
     *
     * @param lblStatusText the text of the status label
     */
    void setLblStatusText(String lblStatusText) {
        this.lblStatusText = lblStatusText;
    }

    /**
     * Sets the context of the status label.
     *
     * @param lblStatusForeground the status of the status label
     * @param lblStatusText the text of the status label
     */
    void setLblStatusContext(Color lblStatusForeground, String lblStatusText) {
        this.lblStatusForeground = lblStatusForeground;
        this.lblStatusText = lblStatusText;
    }

    /**
     * Copies the current model to a new model.
     *
     * @return a fresh copy of the current model
     */
    MainWindowModel copy() {
        return new MainWindowModel(
                btnConnectEnabled,
                btnConnectText,
                btnControlsEnabled,
                btnControlsText,
                btnCameraEnabled,
                btnCameraText,
                btnRecorderEnabled,
                btnRecorderText,
                btnAiEnabled,
                btnAiText,
                lblStatusEnabled,
                lblStatusForeground,
                lblStatusText
        );
    }

}