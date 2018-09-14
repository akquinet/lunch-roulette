package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.User

@Suppress("unused")
class UserRepositoryImpl:EntityRepository<User> {
    override fun prepareForSave(value: User): User = value
}
