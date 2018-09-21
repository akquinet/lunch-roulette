package de.akquinet.ccsp.lunch.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import de.akquinet.ccsp.lunch.data.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.stream.Collectors
import java.util.stream.StreamSupport


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
        val json = response.body!!
        val jsonNode = objectMapper.readTree(json)

        assert(jsonNode.isArray) { "Expected array" }

        val array = jsonNode as ArrayNode

        val places = StreamSupport.stream(array.spliterator(), false)
                .map { node -> objectMapper.treeToValue(node, OpenStreetMapPlace::class.java) }
                .collect(Collectors.toList())

        return json
    }
}

/*
        // https://nominatim.openstreetmap.org/search?format=json&street=66%20Bülowstr&city=Berlin&postalcode=10783

[{"place_id":"41720067","licence":"Data © OpenStreetMap contributors, ODbL 1.0. https:\/\/osm.org\/copyright","osm_type":"node","osm_id":"3073205136","boundingbox":["52.4964692","52.4965692","13.3670048","13.3671048"],"lat":"52.4965192","lon":"13.3670548","display_name":"Munch’s Hus, 66, Bülowstraße, Schöneberg, Tempelhof-Schöneberg, Berlin, 10783, Deutschland","class":"amenity","type":"restaurant","importance":0.431,"icon":"https:\/\/nominatim.openstreetmap.org\/images\/mapicons\/food_restaurant.p.20.png"},{"place_id":"63638968","licence":"Data © OpenStreetMap contributors, ODbL 1.0. https:\/\/osm.org\/copyright","osm_type":"node","osm_id":"5349123380","boundingbox":["52.496155","52.496255","13.3678972","13.3679972"],"lat":"52.496205","lon":"13.3679472","display_name":"systemblick Organisationsentwicklung, 66, Bülowstraße, Schöneberg, Tempelhof-Schöneberg, Berlin, 10783, Deutschland","class":"office","type":"company","importance":0.431},{"place_id":"18967237","licence":"Data © OpenStreetMap contributors, ODbL 1.0. https:\/\/osm.org\/copyright","osm_type":"node","osm_id":"1812241438","boundingbox":["52.4962642","52.4963642","13.3679496","13.3680496"],"lat":"52.4963142","lon":"13.3679996","display_name":"ZWP Ingenieur-AG, 66, Bülowstraße, Schöneberg, Tempelhof-Schöneberg, Berlin, 10783, Deutschland","class":"office","type":"company","importance":0.431},{"place_id":"18761159","licence":"Data © OpenStreetMap contributors, ODbL 1.0. https:\/\/osm.org\/copyright","osm_type":"node","osm_id":"1812240929","boundingbox":["52.4962984","52.4963984","13.3680999","13.3681999"],"lat":"52.4963484","lon":"13.3681499","display_name":"Lautsprecher Teufel, 66, Bülowstraße, Schöneberg, Tempelhof-Schöneberg, Berlin, 10783, Deutschland","class":"office","type":"company","importance":0.431},{"place_id":"83039865","licence":"Data © OpenStreetMap contributors, ODbL 1.0. https:\/\/osm.org\/copyright","osm_type":"way","osm_id":"31541844","boundingbox":["52.4963552","52.4965966","13.366946","13.3675694"],"lat":"52.49647035","lon":"13.3670648902871","display_name":"Gewerbehof Bülowstraße, 66, Bülowstraße, Schöneberg, Tempelhof-Schöneberg, Berlin, 10783, Deutschland","class":"building","type":"yes","importance":0.431}]
 */
