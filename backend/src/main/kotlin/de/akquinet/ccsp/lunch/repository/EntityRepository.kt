package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.AbstractEntity

/**
 * {@see https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations}
 */
interface EntityRepository<T : AbstractEntity> {
    fun prepareForSave(value: T): T
}
