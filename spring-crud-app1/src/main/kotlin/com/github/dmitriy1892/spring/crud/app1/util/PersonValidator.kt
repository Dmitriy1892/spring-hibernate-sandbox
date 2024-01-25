package com.github.dmitriy1892.spring.crud.app1.util

import com.github.dmitriy1892.spring.crud.app1.dao.PersonDao
import com.github.dmitriy1892.spring.crud.app1.models.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class PersonValidator @Autowired constructor(
    private val personDao: PersonDao
) : Validator {

    override fun supports(clazz: Class<*>): Boolean = clazz == Person::class.java

    override fun validate(target: Any, errors: Errors) {
        val person = target as? Person ?: run {
            errors.rejectValue("validation target", "", "Validation target is not a Person")
            return
        }

        val foundPerson = personDao.show(person.email)

        foundPerson.onSuccess {
            errors.rejectValue("email", "", "This email is already taken")
        }
    }
}