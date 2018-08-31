package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.repository.UserRepository
import de.akquinet.ccsp.lunch.data.User
import org.assertj.core.api.Assertions
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class UserRepositoryTest : AbstractSpringTest() {
    @Autowired
    lateinit var repository: UserRepository

    @Test
    fun persistPlain() {
        val user = User("Sarah", "sarah.ganter@gmail.com")
        val saved = txTemplate.execute { repository.save(user) }

        Assertions.assertThat(saved).isSameAs(user)
        Assertions.assertThat(saved?.id).isGreaterThan(0)

        txTemplate.execute {
            val loaded = repository.findByName("Sarah")
            Assertions.assertThat(loaded).isNotSameAs(user).isEqualTo(user)
        }
    }
}
