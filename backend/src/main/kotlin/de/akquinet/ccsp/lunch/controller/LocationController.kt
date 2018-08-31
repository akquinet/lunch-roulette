package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.Location
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/rest/locations")
class LocationController : AbstractController<Location>() {
    override val repository: LunchRepository<Location> get() = injectedRepository

    @Autowired
    lateinit var injectedRepository: LocationRepository
}
