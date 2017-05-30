package nl.ordina.jtech.hackadrone.io

import nl.ordina.jtech.hackadrone.models.Command
import nl.ordina.jtech.hackadrone.net.CommandConnection

import java.io.IOException

class Controller(
        private val device: Device,
        private val commandConnection: CommandConnection
) : Thread(), CommandListener {
    private var command = Command()

    override fun interrupt() {
        device.setListener(null)
        device.stop()
        super.interrupt()
    }

    override fun run() {
        device.setListener(this)
        device.start()

        while (!isInterrupted) {
            try {
                commandConnection.sendCommand(command)
                Thread.sleep(50)
            } catch (e: IOException) {
                System.err.println("Unable to send command")
            } catch (e: InterruptedException) {
                System.err.println("Command interrupted")
            }

        }
    }

    override fun onCommandReceived(command: Command?) {
        this.command = command?:Command()
    }

}
