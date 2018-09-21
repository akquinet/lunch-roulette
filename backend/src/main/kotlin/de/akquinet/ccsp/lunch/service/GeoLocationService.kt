package de.akquinet.ccsp.lunch.service

import com.fasterxml.jackson.databind.ObjectMapper
import de.akquinet.ccsp.lunch.data.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


const val SEARCH_URL = "https://nominatim.openstreetmap.org/search"

@Service
class GeoLocationService {
    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var objectMapper: ObjectMapper

    fun computeGeoLocation(address: Address): String {
        val builder = UriComponentsBuilder.fromHttpUrl(SEARCH_URL)
                .queryParam("format", "json")
                .queryParam("street", address.streetNumber + " " + address.streetName)
                .queryParam("city", address.city)
                .queryParam("postalcode", address.plz)
        val uri = builder.build().toUri()
        val response = restTemplate.getForEntity(uri, String::class.java)

        return response.body!!
    }
}
