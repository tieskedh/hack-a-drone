package nl.ordina.jtech.hackadrone.io

import nl.ordina.jtech.hackadrone.models.Command

import java.awt.*
import java.awt.event.KeyEvent

import java.awt.event.KeyEvent.*

class Keyboard(private val focusManager: KeyboardFocusManager) : Device, KeyEventDispatcher {
    private var commandListener: CommandListener? = null
    private val command = Command()

    override fun start() {
        focusManager.addKeyEventDispatcher(this)
    }

    override fun stop() {
        focusManager.removeKeyEventDispatcher(this)
    }

    override fun setListener(commandListener: CommandListener?) {
        this.commandListener = commandListener
    }

    override fun dispatchKeyEvent(e: KeyEvent): Boolean {
        when(e.id) {
            KEY_PRESSED-> onKeyEvent(e, true)
            KEY_RELEASED-> onKeyEvent(e, false)
        }
        return false
    }

    private fun onKeyEvent(keyEvent: KeyEvent, isPressed: Boolean) {
        val value = if (isPressed) 127 else 0
        var input = true

        when (keyEvent.keyCode) {
            VK_W -> command.pitch = value
            VK_S -> command.pitch = -value
            VK_A -> command.roll = -value
            VK_D -> command.roll = value
            VK_Q -> command.yaw = -value
            VK_E -> command.yaw = value
            VK_LEFT -> command.takeOff = isPressed
            VK_RIGHT -> command.land = isPressed
            VK_UP -> command.throttle = value
            VK_DOWN -> command.throttle = -value
            else -> input = false
        }
        if (input){
            commandListener?.onCommandReceived(command)
        }
        keyEvent.consume()
    }

}
