package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.data.Address
import de.akquinet.ccsp.lunch.data.Location
import org.assertj.core.api.Assertions.assertThat
import org.hibernate.validator.internal.engine.path.PathImpl
import org.junit.Test
import javax.validation.ConstraintViolation
import javax.validation.Validation


class LocationValidationTest : AbstractValidationTest<Location>() {
    @Test
    fun validateName() {
        val location = Location(name = "")
        checkValue(location, "name")
    }

    @Test
    fun validateRating() {
        val location = Location(name = "Jens")
        location.rating = 12
        checkValue(location, "rating")
    }

    @Test
    fun validatePLZ() {
        val location = Location(name = "Thanh Hno")
        location.address = Address(streetName = "Potsdamer Straße", city = "Berlin",
                plz = "783", streetNumber = "122")
        checkValue(location, "address.plz")
    }

    @Test
    fun validateStreetNumber() {
        val location = Location(name = "Thanh Hno")
        location.address = Address(streetName = "Potsdamer Straße", city = "Berlin",
                plz = "10783", streetNumber = "Hallo")
        checkValue(location, "address.streetNumber")
    }

    @Test
    fun validateOK() {
        val location = Location(name = "Thanh Hno")
        location.address = Address(streetName = "Potsdamer Straße", city = "Berlin",
                plz = "10783", streetNumber = "122")
        assertThat(checkValue(location)).isEmpty()
    }
}
