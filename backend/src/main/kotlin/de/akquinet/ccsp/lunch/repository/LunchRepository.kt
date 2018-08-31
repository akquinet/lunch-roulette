package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.AbstractEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface LunchRepository<T : AbstractEntity> : JpaRepository<T, Int>, EntityRepository<T> {
    fun findByName(name: String): T
}
