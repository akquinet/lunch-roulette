package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.Community
import de.akquinet.ccsp.lunch.data.User
import de.akquinet.ccsp.lunch.repository.*
import de.akquinet.ccsp.lunch.rest.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping(CommunityController.PATH)
class CommunityController : LunchController<Community>() {

    override val repository: LunchRepository<Community> get() = injectedRepository

    @Autowired
    lateinit var injectedRepository: CommunityRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @PutMapping("/{name}/add-user", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@PathVariable name: String, @RequestBody @Valid user: User, errors: Errors) {
        check(errors)

        val community = repository.findByName(name) ?: throw EntityNotFoundException(name)

        community.addParticipants(userRepository.prepareForCommunity(user))
        repository.save(community)
    }

    companion object {
        const val PATH = "/rest/communities"
    }
}
