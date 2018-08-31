package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: LunchRepository<User>
