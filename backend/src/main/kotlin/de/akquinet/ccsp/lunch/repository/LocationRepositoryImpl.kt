package de.akquinet.ccsp.lunch.repository

import de.akquinet.ccsp.lunch.data.Location

class LocationRepositoryImpl : EntityRepository<Location> {
    override fun prepareForSave(value: Location): Location = value
}
