package de.akquinet.ccsp.lunch.websocket

import java.net.URI
import javax.websocket.*

@ClientEndpoint(
        encoders = [MessageEncoder::class],
        decoders = [MessageDecoder::class]
)
class MessagingClientEndpoint(endpointURI: URI) {
    private lateinit var userSession: Session
    val messages: MutableList<Message> = ArrayList()

    init {
        println("URI : $endpointURI")

        try {
            val container = ContainerProvider.getWebSocketContainer()
            container.connectToServer(this, endpointURI)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @OnOpen
    fun onOpen(userSession: Session) {
        println("Client opening websocket")
        this.userSession = userSession
    }

    @OnClose
    fun onClose(session: Session, reason: CloseReason) {
        println("Client closing websocket")
    }

    @OnMessage
    fun onMessage(message: Message) {
        messages.add(message)
        println("Client received: $message")
    }

    fun sendMessage(message: Message) {
        userSession.asyncRemote.sendObject(message)
    }
}
