package de.akquinet.ccsp.lunch.data

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Embeddable
data class Address(
        @Column(name = "STREET", length = 256, nullable = false)
        @get:NotBlank
        val streetName: String,

        @Column(name = "NUMBER", length = 5, nullable = false)
        @get:Pattern(regexp = "\\d+[a-zA-Z]?")
        @get:NotBlank
        val streetNumber: String,

        @Column(name = "PLZ", length = 5, columnDefinition = "CHAR(5)", nullable = false)
        @get:Pattern(regexp = "\\d{5}")
        @get:NotBlank
        val plz: String,

        @Column(name = "CITY", length = 256, nullable = false)
        @get:NotBlank
        val city: String
        ) {
    @Column(name = "TELEPHONE", length = 30, nullable = false)
    @NotNull
    var telephoneNumber: String = ""

    var latitude: Float = 52.4962978F

    var longitude: Float = 13.2868977F
}
