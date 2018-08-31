package de.akquinet.ccsp.lunch.websocket

data class Message(
        var from: String = "",
        var to: String = "",
        var content: String = "")
{
    // Necessary for JSON
    @Suppress("unused")
    constructor() : this("", "", "")
}
