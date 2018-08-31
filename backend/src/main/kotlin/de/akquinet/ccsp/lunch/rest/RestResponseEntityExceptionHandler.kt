package de.akquinet.ccsp.lunch.rest

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(InvalidStoreRequest::class)
    protected fun handleInvalidValue(ex: InvalidStoreRequest, request: WebRequest): ResponseEntity<Any> {
        logger.error("Invalid store request", ex)

        return ResponseEntity(ErrorDetails(HttpStatus.BAD_REQUEST, ex.message ?: ""),
                HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(EmptyResultDataAccessException::class)
    protected fun handleEmptyResult(
            ex: EmptyResultDataAccessException, request: WebRequest): ResponseEntity<Any> {
        logger.warn("No data", ex)

        return ResponseEntity(ErrorDetails(HttpStatus.NOT_FOUND, ex.message ?: ""),
                HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    protected fun handleError(
            ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        logger.error("Interhal server error", ex)

        return ResponseEntity(ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, ex.message ?: ""),
                HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
