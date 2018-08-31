package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.Location
import de.akquinet.ccsp.lunch.repository.LocationRepository
import de.akquinet.ccsp.lunch.repository.LunchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(LocationController.PATH)
class LocationController : LunchController<Location>() {
    override val repository: LunchRepository<Location> get() = injectedRepository

    @Autowired
    lateinit var injectedRepository: LocationRepository

    companion object {
        const val PATH = "/rest/locations"
    }
}
