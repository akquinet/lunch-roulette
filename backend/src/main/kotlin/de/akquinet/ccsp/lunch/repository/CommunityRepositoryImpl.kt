package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.Community
import de.akquinet.ccsp.lunch.data.User
import org.springframework.beans.factory.annotation.Autowired
import java.util.stream.Collectors


@Suppress("unused")
class CommunityRepositoryImpl : EntityRepository<Community> {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun prepareForSave(community: Community): Community {
        val founder = makePersistent(community.founder)
        val participants = community.additionalParticipantsOnly().stream()
                .map { u -> makePersistent(u) }
                .collect(Collectors.toList<User>())

        return Community(community.name, founder).also { c ->
            c.addParticipants(participants)
        }
    }

    private fun makePersistent(user: User): User {
        val existingUser = userRepository.findById(user.id)

        return if (existingUser.isPresent)
            existingUser.get().also { u -> u.community = null }
        else
            userRepository.save(user.also { u -> u.community = null })
    }
}
