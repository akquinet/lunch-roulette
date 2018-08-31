package de.akquinet.ccsp.lunch.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import javax.websocket.Encoder
import javax.websocket.EndpointConfig


class MessageEncoder: Encoder.Text<Message> {
    private val mapper = ObjectMapper()

    override fun destroy() {
    }

    override fun init(endpointConfig: EndpointConfig?) {
    }

    override fun encode(message: Message): String {
        return mapper.writeValueAsString(message)
    }
}
