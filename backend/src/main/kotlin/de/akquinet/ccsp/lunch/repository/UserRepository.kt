package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : LunchRepository<User>

fun UserRepository.prepareForCommunity(user: User): User {
    val existingUser = findById(user.id)

    return if (existingUser.isPresent)
        existingUser.get().also { u -> u.community = null }
    else
        save(user.also { u -> u.community = null })
}
