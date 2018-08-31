package de.akquinet.ccsp.lunch

import org.assertj.core.api.Assertions.assertThat
import org.hibernate.validator.internal.engine.path.PathImpl
import javax.validation.ConstraintViolation
import javax.validation.Validation


abstract class AbstractValidationTest<T> {
    protected fun checkValue(value: T, invalidPropertyName: String) {
        val constraints = checkValue(value)

        assertThat(constraints).extracting("propertyPath")
                .contains(PathImpl.createPathFromString(invalidPropertyName))
    }

    protected fun checkValue(value: T): Set<ConstraintViolation<T>> =
            Validation.buildDefaultValidatorFactory().validator.validate(value)
}
