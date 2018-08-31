package de.akquinet.ccsp.lunch.data

import com.fasterxml.jackson.annotation.JsonManagedReference
import de.akquinet.ccsp.lunch.repository.addParticipants
import javax.persistence.*


@Entity
@Table(name = "COMMUNITIES")
class Community(name: String,

                @ManyToOne(cascade = [CascadeType.PERSIST])
                val founder: User)
    : AbstractEntity(name = name)
{
    @OneToMany(mappedBy = "community")
    @get:JsonManagedReference
    val participants: MutableSet<User> = LinkedHashSet()

    init {
        addParticipants(founder)
    }
}
