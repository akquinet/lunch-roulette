package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/rest/users")
class UserController : AbstractController<User>() {
    override val repository: LunchRepository<User> get() = injectedRepository

    @Autowired
    lateinit var injectedRepository: UserRepository
}
