package org.paulobichara.kotlin

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class NewUserDto(

    @get: Email(message = "{validation.email.notValid}")
    @get: NotBlank(message = "{validation.email.notBlank}")
    val email: String,

    @get: Length(min = 8, message = "{validation.password.notValid}")
    @get: NotBlank(message = "{validation.password.notBlank}")
    val password: String

)