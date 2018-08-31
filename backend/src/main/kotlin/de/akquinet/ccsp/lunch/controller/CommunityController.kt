package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.Community
import de.akquinet.ccsp.lunch.repository.CommunityRepository
import de.akquinet.ccsp.lunch.repository.LunchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(CommunityController.PATH)
class CommunityController : LunchController<Community>() {

    override val repository: LunchRepository<Community> get() = injectedRepository

    @Autowired
    lateinit var injectedRepository: CommunityRepository

    companion object {
        const val PATH = "/rest/communities"
    }
}
