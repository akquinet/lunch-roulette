package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.Community
import de.akquinet.ccsp.lunch.data.User
import org.springframework.beans.factory.annotation.Autowired
import java.util.stream.Collectors


class CommunityRepositoryImpl : EntityRepository<Community> {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun prepareForSave(community: Community): Community {
        val founder = makePersistent(community.founder)
        val participants = community.additionalParticipantsOnly().stream()
                .map { u -> makePersistent(u) }
                .collect(Collectors.toList<User>())

        return Community(community.name, founder).also { community ->
            community.addParticipants(participants)
        }
    }

    private fun makePersistent(user: User): User {
        val existingUser: User? = userRepository.findById(user.id).orElseGet { null }

        if (existingUser != null) {
            existingUser.community = null
            return existingUser
        } else {
            user.community = null
            return userRepository.save(user)
        }
    }
}
