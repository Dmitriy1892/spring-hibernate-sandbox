package com.github.dmitriy1892.spring.crud.app1.models

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class PersonMutable(
    var id: Int = 0,

    @field:NotEmpty(message = "Name should not be empty")
    @field:Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    var name: String = "",

    @field:Min(value = 0, message = "Age should be greater than 0")
    var age: Int = 0,

    @field:NotEmpty(message = "Email should not be empty")
    @field:Email(message = "Email should be valid")
    var email: String = "",

    @field:Pattern(
        regexp = "[A-Z][a-z]+, [A-Z][a-z]+, [0-9]{6}",
        message = "Your address shout be in this format: Country, City, Postal Code (6 digits)"
    )
    var address: String = ""
)