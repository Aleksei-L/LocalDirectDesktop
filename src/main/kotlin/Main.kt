package com.localdirect.desktop

import java.net.ServerSocket

const val LOCALDIRECT_HANDSHAKE = "com.localdirect.handshake"
const val LOCALDIRECT_ACCEPT = "com.localdirect.accept"
const val LOCALDIRECT_SOCKET_PORT = 9999

fun main() {
    val serverSocket = ServerSocket(LOCALDIRECT_SOCKET_PORT)

    try {
        while (true) {
            println("Server started")

            val socket = serverSocket.accept()
            val dataArray = ByteArray(LOCALDIRECT_HANDSHAKE.length)
            val input = socket.getInputStream()
            input.readNBytes(dataArray, 0, LOCALDIRECT_HANDSHAKE.length)

            println("Received via TCP: ${dataArray.toString(Charsets.US_ASCII)}")

            val output = socket.getOutputStream()
            val outputArray = LOCALDIRECT_ACCEPT.toByteArray(Charsets.US_ASCII)
            output.write(outputArray, 0, outputArray.size)

            input.close()
            output.close()

            println("Sent")
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
}