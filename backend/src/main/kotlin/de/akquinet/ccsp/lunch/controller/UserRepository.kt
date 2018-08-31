package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: LunchRepository<User>
