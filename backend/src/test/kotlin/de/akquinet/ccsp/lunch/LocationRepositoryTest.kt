package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.controller.LocationRepository
import de.akquinet.ccsp.lunch.controller.addAlias
import de.akquinet.ccsp.lunch.data.Address
import de.akquinet.ccsp.lunch.data.Location
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import java.time.Duration
import java.time.temporal.ChronoUnit

class LocationRepositoryTest : AbstractSpringTest() {
    @Autowired
    lateinit var repository: LocationRepository

    @Test
    fun persistPlain() {
        val location = Location("Restaurant")
        location.addAlias("Hippe")

        val saved = txTemplate.execute { repository.save(location) }

        Assertions.assertThat(saved).isSameAs(location)
        Assertions.assertThat(saved?.id).isGreaterThan(0)

        txTemplate.execute {
            val loaded = repository.findById(saved.id)
            Assertions.assertThat(loaded.isPresent).isTrue()
            Assertions.assertThat(loaded.get()).isNotSameAs(location)
            Assertions.assertThat(loaded.get().aliases).containsExactly("Hippe")
        }
    }

    @Test(expected = EmptyResultDataAccessException::class)
    fun findByName() {
        repository.findByName("Gibt's nicht")
    }

    @Test
    fun duration() {
        val location = Location( "Jens")
        location.averageDuration = Duration.of(10, ChronoUnit.MINUTES)
        repository.save(location)

        val loaded = repository.findById(location.id)
        Assertions.assertThat(loaded.isPresent).isTrue()
        Assertions.assertThat(loaded.get().averageDuration)
                .isEqualTo(Duration.of(10, ChronoUnit.MINUTES))
    }

    @Test
    fun address() {
        val location = Location(name = "Thanh Hno")
        location.addAlias("Taschen-Thai", "Tassilo-Thai")

        location.address = Address("Potsdamer Straße", city = "Berlin",
                plz = "10783", streetNumber = "133").apply {
            telephoneNumber = "+49 30 2153944"
        }

        txTemplate.execute { repository.save(location) }
        txTemplate.execute {
            val location2 = repository.findById(location.id).orElseThrow { IllegalStateException("Must not be null") }
            assertThat(location2).isNotSameAs(location)
            assertThat(location2.address.streetName).isEqualTo("Potsdamer Straße")
            Assertions.assertThat(location2.aliases).hasSize(2)
        }
    }

    @Test
    fun nameConstraintInDb() {
        val location = Location("")

        assertThatThrownBy {
            txTemplate.execute {
                repository.save(location)
            }
        }.hasStackTraceContaining("propertyPath=name")
    }
}
