package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.websocket.Message
import de.akquinet.ccsp.lunch.websocket.MessageDecoder
import de.akquinet.ccsp.lunch.websocket.MessageEncoder
import org.junit.Assert
import org.junit.Test


class MessageEncoderTest {
    @Test
    fun decode() {
        Assert.assertEquals(MESSAGE, MessageDecoder().decode(JSON))
        Assert.assertEquals(JSON, MessageEncoder().encode(MESSAGE))
    }

    companion object {
        const val JSON = "{\"from\":\"from\",\"to\":\"to\",\"content\":\"Hello\"}"
        val MESSAGE = Message("from", "to", "Hello")
    }
}
