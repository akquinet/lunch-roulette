package de.akquinet.ccsp.lunch.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidStoreRequest(message: String) : RuntimeException(message)
