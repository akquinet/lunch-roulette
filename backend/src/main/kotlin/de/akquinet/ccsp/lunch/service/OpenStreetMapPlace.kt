package de.akquinet.ccsp.lunch.service

import com.fasterxml.jackson.annotation.JsonProperty


class OpenStreetMapPlace {
    @JsonProperty("place_id")
    var placeId: String = ""

    @JsonProperty("display_name")
    var displayName: String = ""

    @JsonProperty("lon")
    var longitude: Float= 0F

    @JsonProperty("lat")
    var latitude: Float= 0F
}
