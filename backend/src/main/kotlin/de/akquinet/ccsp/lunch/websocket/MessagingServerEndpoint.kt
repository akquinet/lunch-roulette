package de.akquinet.ccsp.lunch.websocket

import de.akquinet.ccsp.lunch.websocket.MessagingServerEndpoint.Companion.PATH
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint

// http://www.baeldung.com/java-websockets
@ServerEndpoint(
        value = "$PATH/{user}",
        encoders = [MessageEncoder::class],
        decoders = [MessageDecoder::class]
)
class MessagingServerEndpoint {
    private lateinit var session: Session
    private lateinit var user: String

    @OnOpen
    fun onOpen(session: Session, @PathParam("user") user: String) {
        this.session = session
        this.user = user
        ENDPOINTS.add(this)

        val answer = Message("server", "$user", "Hello World, $user!")
        sendMessage(message = answer)
    }

    @OnMessage
    fun onMessage(message: Message) {
        println("Server received message $message")
        val answer = Message("server", "$user", "Thank you, $user!")
        sendMessage(message = answer)
    }

    @OnClose
    fun onClose() {
        ENDPOINTS.remove(this)
        println("Server says goodbye to $user")
    }

    @OnError
    fun onError(session: Session, throwable: Throwable) {
        System.err.println(throwable)
    }

    private fun broadcastMessage(message: Message) {
        ENDPOINTS.forEach { endpoint ->
            sendMessage(endpoint, message)
        }
    }

    private fun sendMessage(endpoint: MessagingServerEndpoint = this, message: Message) {
        synchronized(endpoint) {
            try {
                endpoint.session.basicRemote.sendObject(message)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val PATH = "/ws-messaging"
        private val ENDPOINTS: MutableSet<MessagingServerEndpoint> = LinkedHashSet()
    }
}
