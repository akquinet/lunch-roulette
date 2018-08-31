package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.data.Community
import de.akquinet.ccsp.lunch.data.User
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

class CommunityControllerTest : AbstractSpringTest() {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun checkLookupName() {
        val user = User("Ganter", "sarah.ganter@akquinet.de")
        testRestTemplate.postForEntity("/rest/users/store", user, Int::class.java)

        val result = testRestTemplate
                .getForEntity("/rest/users/Ganter", User::class.java)

        assertEquals(HttpStatus.OK, result?.statusCode)

        val community = Community("ats", user)



    }
}
