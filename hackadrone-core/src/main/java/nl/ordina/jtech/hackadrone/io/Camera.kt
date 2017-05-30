package nl.ordina.jtech.hackadrone.io

import nl.ordina.jtech.hackadrone.utils.OS

import java.io.IOException

class Camera(
        droneHost: String,
        dronePort: Int,
        videoHost: String,
        videoPort: Int
) : ScreenCapturer(
        droneHost,
        dronePort,
        videoHost,
        videoPort,
        "camera"
) {
    @Throws(IOException::class)
    override protected fun getProcess(): Process {
        return when (OS.os) {
            "win" -> ProcessBuilder("cmd", "/c", "start", VIDEO_PATH + "/win/ffplay.exe", "-probesize", "64", "-sync", "ext", output)
            "unix"-> ProcessBuilder(VIDEO_PATH + "/unix/ffplay", "-fflags", "nobuffer", output)
            "osx" -> ProcessBuilder(VIDEO_PATH + "/osx/ffplay", "-fflags", "nobuffer", output)
            else -> throw UnsupportedOperationException("The OS is not supported")
        }.start()
    }
}
