package de.akquinet.ccsp.lunch.controller

import de.akquinet.ccsp.lunch.data.AbstractEntity
import de.akquinet.ccsp.lunch.rest.InvalidStoreRequest
import org.springframework.http.MediaType
import org.springframework.validation.Errors
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.stream.Collectors
import javax.validation.ConstraintViolation
import javax.validation.Valid


abstract class AbstractController<T : AbstractEntity> {
    abstract val repository: LunchRepository<T>

    @GetMapping("/")
    fun findAll(): List<T> = repository.findAll()

    @GetMapping("/{name}")
    fun findByName(@PathVariable name: String): T? = repository.findByName(name)

    @PostMapping("/store", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun store(@RequestBody @Valid value: T, errors: Errors): Int {
        check(errors)

        return repository.save(value).id
    }

    companion object {
        private fun check(errors: Errors) {
            if (errors.hasErrors()) {
                throw InvalidStoreRequest(createErrorString(errors))
            }
        }

        private fun createErrorString(errors: Errors) =
                errors.allErrors.stream().map { e -> mapObjectError(e) }
                        .collect(Collectors.joining("\n"))

        private fun mapObjectError(e: ObjectError?): String {
            val constraintViolation = e?.takeIf { e.contains(ConstraintViolation::class.java) }
                    ?.unwrap(ConstraintViolation::class.java)

            return constraintViolation?.let { cv -> cv.propertyPath.toString() + ": " + cv.message }
                    ?: e.toString()
        }
    }
}
