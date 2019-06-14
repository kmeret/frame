package io.github.kmeret.frame.infrastructure.data.network

import java.net.URI

data class ServerConfig(
    val endpoint: String,
    val pin: String? = null
) {
    val host: String =  URI(endpoint).host
}