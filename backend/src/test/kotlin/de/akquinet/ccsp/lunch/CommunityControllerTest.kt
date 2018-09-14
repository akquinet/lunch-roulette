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
import org.springframework.http.*


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

    @Test
    fun checkAddUser() {
        val sarah = User("SarahGanter", "sarahganter@web.de")
        val community = Community("lunch", sarah)
        community.participants.clear()

        testRestTemplate.postForEntity(CommunityController.PATH + STORE, community, Int::class.java)

        val user = User("MarkusDahm", "markusdahm@web.de")
        testRestTemplate.postForEntity(UserController.PATH + STORE, user, Int::class.java)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(user, headers)

        val addResponse= testRestTemplate.exchange(
                CommunityController.PATH + "/lunch/add-user",
                HttpMethod.PUT, entity, String::class.java)
        assertEquals(HttpStatus.OK, addResponse?.statusCode)

        val communityResponse = testRestTemplate
                .getForEntity(CommunityController.PATH + "/lunch", Community::class.java)
        assertEquals(HttpStatus.OK, communityResponse?.statusCode)

        assertEquals(2, communityResponse.body?.participants?.size)
    }
}
