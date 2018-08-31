package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.repository.LocationRepository
import de.akquinet.ccsp.lunch.repository.addAlias
import de.akquinet.ccsp.lunch.data.Address
import de.akquinet.ccsp.lunch.data.Location
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component


@Component
class ApplicationStartup : ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private lateinit var locationRepository: LocationRepository

    @Autowired
    private lateinit var env: Environment

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        if (env.getProperty("lunchroulette.system") != "TEST") {
            val vietnam = Location(name = "Thanh Hno")
            vietnam.style = "Vietnamesisch"
            vietnam.addAlias("Taschen-Thai", "Tassilo-Thai")
            vietnam.address = Address(streetName = "Potsdamer Straße", city = "Berlin",
                    plz = "10783", streetNumber = "122")

            val norwegen = Location(name = "Munch's Hus")
            norwegen.style = "Norwegisch"
            norwegen.addAlias("Norweger")
            norwegen.address = Address("Bülowstr.", "66", "10783", "Berlin")

            locationRepository.save(vietnam)
            locationRepository.save(norwegen)
        }
    }

}
