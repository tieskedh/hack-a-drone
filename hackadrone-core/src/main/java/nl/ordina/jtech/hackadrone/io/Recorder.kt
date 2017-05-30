package nl.ordina.jtech.hackadrone.io

import nl.ordina.jtech.hackadrone.utils.OS

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class Recorder(
        droneHost: String,
        dronePort: Int,
        videoHost: String,
        videoPort: Int)
    : ScreenCapturer(
        droneHost,
        dronePort,
        videoHost,
        videoPort,
        "recorder"
) {
    @Throws(IOException::class)

    override protected fun getProcess() : Process{
        val timestamp = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date())
        val fileName = "record-$timestamp.mp4"

        return ProcessBuilder(
         when (OS.os) {
            "win" -> listOf("cmd", "/c", "start", VIDEO_PATH + "/win/ffmpeg.exe")
            "unix" -> listOf(VIDEO_PATH + "/unix/ffmpeg")
            "osx" -> listOf(VIDEO_PATH + "/osx/ffmpeg")
            else -> throw UnsupportedOperationException("OS not supported")
        } + listOf("-f", "h264", "-i", output, "-vcodec", "copy", "-r", "25", fileName)).start()
    }
}
