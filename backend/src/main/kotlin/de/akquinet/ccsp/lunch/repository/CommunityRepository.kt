package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.Community
import de.akquinet.ccsp.lunch.data.User
import org.springframework.stereotype.Repository
import java.util.stream.Collectors

@Repository
interface CommunityRepository : LunchRepository<Community>

fun Community.additionalParticipantsOnly(): List<User> = participants.stream().filter{ u->u != founder}
        .collect(Collectors.toList())

fun Community.addParticipants(vararg users: User): Community = addParticipants(users.asList())

fun Community.addParticipants(users: List<User>): Community {
    this.participants.addAll(users)
    users.forEach { user -> user.community = this }
    return this
}
