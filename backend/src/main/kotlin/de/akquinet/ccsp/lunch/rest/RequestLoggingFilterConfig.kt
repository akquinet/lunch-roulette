package de.akquinet.ccsp.lunch.rest

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.filter.CommonsRequestLoggingFilter

@Configuration
class RequestLoggingFilterConfig {
    @Bean
    fun logFilter(): CommonsRequestLoggingFilter {
        val filter = CommonsRequestLoggingFilter()
        filter.setIncludeQueryString(true)
        filter.setIncludePayload(true)
        filter.setMaxPayloadLength(10000)
        filter.setIncludeHeaders(false)
        filter.setAfterMessagePrefix("REQUEST DATA : ")
        return filter
    }

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        // Do any additional configuration here
        return builder.build()
    }
}
