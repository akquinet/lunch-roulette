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
        val founder = userRepository.prepareForCommunity(community.founder)
        val participants = community.additionalParticipantsOnly().stream()
                .map { u -> userRepository.prepareForCommunity(u) }
                .collect(Collectors.toList<User>())

        return Community(community.name, founder).also { c ->
            c.addParticipants(participants)
        }
    }
}
