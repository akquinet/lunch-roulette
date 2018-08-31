package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.User
import de.akquinet.ccsp.lunch.repository.LunchRepository
import de.akquinet.ccsp.lunch.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(UserController.PATH)
class UserController : LunchController<User>() {
    override val repository: LunchRepository<User> get() = injectedRepository

    @Autowired
    lateinit var injectedRepository: UserRepository

    companion object {
        const val PATH = "/rest/users"
    }
}
