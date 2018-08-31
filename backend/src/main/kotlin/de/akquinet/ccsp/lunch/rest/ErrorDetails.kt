package de.akquinet.ccsp.lunch.rest

import org.springframework.http.HttpStatus


data class ErrorDetails(val statusCode:HttpStatus, val message:String)
