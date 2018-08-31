package de.akquinet.ccsp.lunch.data

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotBlank

@MappedSuperclass
abstract class AbstractEntity(
        @Id @GeneratedValue
        val id: Int = 0,

        @get:NotBlank
        @Column(unique = true)
        val name: String = ""
) {
    final override fun equals(that: Any?): Boolean =
            that is AbstractEntity && that.javaClass === this.javaClass
                    && that.id == this.id && this.name == that.name

    final override fun hashCode(): Int = id.hashCode() + name.hashCode()
}
