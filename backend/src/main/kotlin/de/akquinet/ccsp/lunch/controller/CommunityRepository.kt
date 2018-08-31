package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.Community
import de.akquinet.ccsp.lunch.data.User
import org.springframework.stereotype.Repository

@Repository
interface CommunityRepository: LunchRepository<Community>

fun Community.addParticipant(vararg users: User) {
    this.participants.addAll(users)
    users.forEach { user -> user.community=this }
}
