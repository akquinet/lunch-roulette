package de.akquinet.ccsp.lunch.service

import de.akquinet.ccsp.lunch.AbstractSpringTest
import de.akquinet.ccsp.lunch.data.Address
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class GeoLocationServiceTest :AbstractSpringTest(){
    @Autowired
    lateinit var geoLocationService: GeoLocationService

    @Test
    fun checkGeoLocation() {
        val address = Address("Bülowstr", "66", "10783", "Berlin")

        geoLocationService.computeGeoLocation(address)
    }
}
