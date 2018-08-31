package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.Location
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : LunchRepository<Location>

fun Location.addAlias(vararg aliases: String) {
    this.aliases.addAll(aliases)
}
