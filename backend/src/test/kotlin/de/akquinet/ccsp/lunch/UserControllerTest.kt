package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.controller.LOOKUP
import de.akquinet.ccsp.lunch.controller.STORE
import de.akquinet.ccsp.lunch.controller.UserController
import de.akquinet.ccsp.lunch.data.User
import org.assertj.core.api.Assertions
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

class UserControllerTest : AbstractSpringTest() {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun checkValidationEmail() {
        val user = User("Thanh Hno", "nonsense")

        val response = testRestTemplate.postForEntity(UserController.PATH + STORE, user, Any::class.java)
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun checkStore() {
        val user = User("Markus", "markus.dahm@gmail.com")
        testRestTemplate.postForEntity(UserController.PATH + STORE, user, Int::class.java)

        val result = testRestTemplate
                .getForEntity(UserController.PATH + LOOKUP + "/Markus", User::class.java)

        assertEquals(HttpStatus.OK, result?.statusCode)
        Assertions.assertThat(result?.body?.name).isEqualTo("Markus")
        Assertions.assertThat(result?.body?.id).isGreaterThan(0)
    }
}
