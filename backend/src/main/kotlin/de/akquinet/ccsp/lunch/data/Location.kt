package de.akquinet.ccsp.lunch.data

import java.time.Duration
import java.time.temporal.ChronoUnit
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Entity
@Table(name = "LOCATIONS")
class Location(name: String) : AbstractEntity(name = name) {
    @NotNull
    var style: String = "Unbekannt"

    @ElementCollection
    val aliases: MutableList<String> = ArrayList()

    @Valid
    var address: Address = Address("Bülowstr.", "66", "10783", "Berlin")

    var averageDuration: Duration = Duration.of(30, ChronoUnit.MINUTES)

    @Min(0)
    @Max(5)
    var rating: Int = 2
}
