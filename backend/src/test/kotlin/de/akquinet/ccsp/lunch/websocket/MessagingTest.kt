package de.akquinet.ccsp.lunch.websocket

import de.akquinet.ccsp.lunch.AbstractSpringTest
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.springframework.boot.web.server.LocalServerPort
import java.net.URI


class MessagingTest : AbstractSpringTest() {
    private lateinit var client: MessagingClientEndpoint

    @LocalServerPort
    private var randomPort: Int = 0

    @Before
    fun setup() {
        client = MessagingClientEndpoint(URI("ws://localhost:$randomPort/"
                + MessagingServerEndpoint.PATH + "/jens"))
    }

    @Test
    fun helloWorld() {
        client.sendMessage(Message("jens", "server", "Ich bin's!"))

        Thread.sleep(500)

        Assertions.assertThat(client.messages).hasSize(2)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val clientEndpoint = MessagingClientEndpoint(URI("ws://localhost:8080/"
                    + MessagingServerEndpoint.PATH + "/hippe"))
//            clientEndpoint.sendText("{'from':'client','to':'server','content':'Ich bins'}".replace('\'', '"'))
            clientEndpoint.sendMessage(Message("from", "to", "content"))
        }
    }
}

