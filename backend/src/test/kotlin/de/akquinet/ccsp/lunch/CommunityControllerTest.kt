package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.controller.CommunityController
import de.akquinet.ccsp.lunch.controller.STORE
import de.akquinet.ccsp.lunch.controller.UserController
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
    fun checkStore() {
        val user = User("Ganter", "sarah.ganter@akquinet.de")
        testRestTemplate.postForEntity(UserController.PATH + STORE, user, Int::class.java)

        val userResponse = testRestTemplate
                .getForEntity(UserController.PATH + "/Ganter", User::class.java)

        assertEquals(HttpStatus.OK, userResponse?.statusCode)

        val community = Community("akquinet tech@spree", userResponse.body!!)

        testRestTemplate.postForEntity(CommunityController.PATH + STORE, community, Int::class.java)

        val communityResponse = testRestTemplate
                .getForEntity(CommunityController.PATH + "/akquinet tech@spree", Community::class.java)
        assertEquals(HttpStatus.OK, communityResponse?.statusCode)
    }
}
