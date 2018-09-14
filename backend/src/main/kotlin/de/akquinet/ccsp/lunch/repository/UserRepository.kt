package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : LunchRepository<User>

fun UserRepository.prepareForCommunity(user: User): User {
    val existingUser = findByName(user.name)

    return existingUser?.also { u -> u.community = null }
            ?: save(user.also { u -> u.community = null })
}
