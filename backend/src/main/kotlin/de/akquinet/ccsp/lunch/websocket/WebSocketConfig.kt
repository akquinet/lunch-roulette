package de.akquinet.ccsp.lunch.websocket

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.server.standard.ServerEndpointExporter

// https://spring.io/blog/2013/05/23/spring-framework-4-0-m1-websocket-support
@Configuration
@ConditionalOnWebApplication
class WebSocketConfig  {
    @Bean
    fun messagingEndpoint() = MessagingServerEndpoint()

    @Bean
    fun endpointExporter(): ServerEndpointExporter = ServerEndpointExporter()
}
