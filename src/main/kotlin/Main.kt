package com.localdirect.desktop

import java.net.ServerSocket

fun main() {
    val serverSocket = ServerSocket(LOCALDIRECT_PORT)
    val handlers = Handlers()
    var state = State.IDLE

    try {
        println("Server started")
        val socket = serverSocket.accept()
        val inputStream = socket.getInputStream()
        val outputStream = socket.getOutputStream()
        val dataArray = ByteArray(256)

        lateinit var clientIp: String

        Runtime.getRuntime().addShutdownHook(Thread {
            println("Cancellation signal received! Cleaning up...")
            outputStream.write(
                ServerConsts.LOCALDIRECT_TERMINAL.toByteArray(Charsets.US_ASCII),
                0,
                ServerConsts.LOCALDIRECT_TERMINAL.length
            )
        })

        while (true) {
            inputStream.read(dataArray)
            val message = handleSocketMessage(dataArray)
            dataArray.fill(0)
            println("Received via TCP: $message")

            when (message) {
                ClientConst.LOCALDIRECT_HANDSHAKE -> {
                    if (state == State.IDLE) {
                        clientIp = socket.inetAddress.toString().substringAfter('/')
                        println("clientIp=$clientIp")

                        val outputArray = ServerConsts.LOCALDIRECT_ACCEPT.toByteArray(Charsets.US_ASCII)
                        outputStream.write(outputArray, 0, outputArray.size)
                        println("ACCEPT sent")
                        state = State.READY_TO_ESTABLISH
                    }
                }

                ClientConst.LOCALDIRECT_ESTABLISH -> {
                    if (state == State.READY_TO_ESTABLISH) {

                        handlers.sendLeftMouseClick()
                        println("Left click")
                        state = State.CONNECTED
                    }
                }

                else -> {}
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun handleSocketMessage(buffer: ByteArray): String {
    println("buffer=${buffer.contentToString()}")
    return buffer.sliceArray(0..<(buffer.indexOfFirst { it == 0.toByte() }))
        .toString(Charsets.US_ASCII)
}
