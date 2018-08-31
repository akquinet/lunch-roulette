package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.data.Community
import de.akquinet.ccsp.lunch.data.User
import de.akquinet.ccsp.lunch.repository.CommunityRepository
import org.assertj.core.api.Assertions
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class CommunityRepositoryTest : AbstractSpringTest() {
    @Autowired
    lateinit var repository: CommunityRepository

    @Test
    fun persistPlain() {
        val sarah = User("diswutz", "diswutz@gmx.net")
        val community = Community("tech@spree", sarah)

        Assertions.assertThat(community.participants).contains(sarah)

        txTemplate.execute { repository.save(community) }

        txTemplate.execute {
            val loaded = repository.findByName("tech@spree")
            Assertions.assertThat(loaded).isEqualTo(community)
            Assertions.assertThat(loaded.participants).contains(sarah)
        }
    }
}
