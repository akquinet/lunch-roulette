package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.controller.LOOKUP
import de.akquinet.ccsp.lunch.controller.LocationController
import de.akquinet.ccsp.lunch.controller.STORE
import de.akquinet.ccsp.lunch.data.Address
import de.akquinet.ccsp.lunch.data.Location
import org.assertj.core.api.Assertions
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

class LocationControllerTest : AbstractSpringTest() {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun checkValidationCity() {
        val location = Location("Thanh Hno")

        location.address = Address(streetName = "Potsdamer Straße", city = " ",
                plz = "10783", streetNumber = "122")
        val response = testRestTemplate.postForEntity(LocationController.PATH + STORE, location, Any::class.java)
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        val errorText = response.body.toString()

        Assertions.assertThat(errorText).contains("address.city")
    }

    @Test
    fun checkValidationPLZ() {
        val location = Location("Thanh Hno")

        location.address = Address(streetName = "Potsdamer Straße", city = "Berlin",
                plz = "783", streetNumber = "122")
        val response = testRestTemplate.postForEntity(LocationController.PATH + STORE, location, Any::class.java)
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        val errorText = response.body.toString()

        Assertions.assertThat(errorText).contains("address.plz")
    }

    @Test
    fun notFound() {
        val result = testRestTemplate
                .getForEntity(LocationController.PATH + LOOKUP + "/BurgerKing", Any::class.java)

        assertEquals(HttpStatus.NOT_FOUND, result?.statusCode)
    }

    @Test
    fun checkLookupName() {
        val location = Location("McDonalds")
        testRestTemplate.postForEntity(LocationController.PATH + STORE, location, Int::class.java)

        val result = testRestTemplate
                .getForEntity(LocationController.PATH + LOOKUP + "/McDonalds", Location::class.java)

        assertEquals(HttpStatus.OK, result?.statusCode)
        Assertions.assertThat(result?.body?.name).isEqualTo("McDonalds")
        Assertions.assertThat(result?.body?.id).isGreaterThan(0)
    }
}
