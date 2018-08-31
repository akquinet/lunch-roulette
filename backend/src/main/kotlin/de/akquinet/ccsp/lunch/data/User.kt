package de.akquinet.ccsp.lunch.data

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.Pattern


@Entity
@Table(name = "USERS")
class User(name: String,
           @get:Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                   flags = [Pattern.Flag.CASE_INSENSITIVE])
           @Column(unique = true)
           val email: String) : AbstractEntity(name = name) {
    @ManyToOne
    @get:JsonBackReference
    var community: Community? = null
}
