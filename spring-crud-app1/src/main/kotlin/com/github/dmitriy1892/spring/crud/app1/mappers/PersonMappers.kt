package com.github.dmitriy1892.spring.crud.app1.mappers

import com.github.dmitriy1892.spring.crud.app1.models.Person
import com.github.dmitriy1892.spring.crud.app1.models.PersonMutable

fun Person.mapToPersonMutable(): PersonMutable = PersonMutable(
    id = id,
    name = name,
    age = age,
    email = email,
    address = address
)

fun PersonMutable.mapToPerson(): Person = Person(
    id = id,
    name = name,
    age = age,
    email = email,
    address = address
)