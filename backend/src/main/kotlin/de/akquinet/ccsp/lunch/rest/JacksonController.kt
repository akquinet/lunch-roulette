package de.akquinet.ccsp.lunch.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


@Configuration
class JacksonController{
    @Autowired
    private lateinit var builder: Jackson2ObjectMapperBuilder

    @Bean
    fun objectMapper(): ObjectMapper =
        builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT).build()
}
