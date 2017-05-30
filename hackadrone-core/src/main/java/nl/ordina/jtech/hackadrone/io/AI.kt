package nl.ordina.jtech.hackadrone.io

import nl.ordina.jtech.hackadrone.models.Command

class AI : Device {

    private var commandListener: CommandListener? = null
    private var command = Command()

    override fun start() {
        command.takeOff = true
        commandListener?.onCommandReceived(command)

        for (i in 0..24) {
            command.throttle = -127
            commandListener?.onCommandReceived(command)
        }
    }

    override fun stop() {
        commandListener = null
        command = Command()
    }

    override fun setListener(commandListener: CommandListener?) {
        this.commandListener = commandListener
    }

}
