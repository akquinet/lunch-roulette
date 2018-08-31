package de.akquinet.ccsp.lunch.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import javax.websocket.Decoder
import javax.websocket.EndpointConfig


class MessageDecoder : Decoder.Text<Message> {
    private val mapper = ObjectMapper()

    override fun decode(message: String): Message {
        return mapper.readValue(message, Message::class.java)
    }

    override fun willDecode(s: String?): Boolean {
        return s != null
    }

    override fun init(endpointConfig: EndpointConfig) {
        // Custom initialization logic
    }

    override fun destroy() {
        // Close resources
    }

}
