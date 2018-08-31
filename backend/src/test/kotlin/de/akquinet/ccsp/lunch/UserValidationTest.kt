package de.akquinet.ccsp.lunch

import de.akquinet.ccsp.lunch.data.User
import org.junit.Test


class UserValidationTest : AbstractValidationTest<User>() {
    @Test
    fun validateEmail() {
        val user = User("Jens", "https://kotlinlang.org/")
        checkValue(user, "email")
    }
}
