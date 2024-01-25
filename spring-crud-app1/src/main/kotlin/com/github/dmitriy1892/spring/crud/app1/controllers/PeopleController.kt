package com.github.dmitriy1892.spring.crud.app1.controllers

import com.github.dmitriy1892.spring.crud.app1.dao.PersonDao
import com.github.dmitriy1892.spring.crud.app1.mappers.mapToPerson
import com.github.dmitriy1892.spring.crud.app1.mappers.mapToPersonMutable
import com.github.dmitriy1892.spring.crud.app1.models.PersonMutable
import com.github.dmitriy1892.spring.crud.app1.util.PersonValidator
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/people")
class PeopleController @Autowired constructor(
    private val personDao: PersonDao,
    private val personValidator: PersonValidator
) {

    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("people", personDao.index())

        return "people/index"
    }

    @GetMapping("/{id}")
    fun show(
        @PathVariable("id") id: Int,
        model: Model
    ): String {
        model.addAttribute("person", personDao.show(id))

        return "people/show"
    }

    @GetMapping("/new")
    fun newPerson(@ModelAttribute("person") personMutable: PersonMutable): String {
        return "people/new"
    }

    @PostMapping
    fun create(
        @ModelAttribute("person")
        @Valid
        personMutable: PersonMutable,
        bindingResult: BindingResult
    ): String {
        personValidator.validate(personMutable.mapToPerson(), bindingResult)

        if (bindingResult.hasErrors()) return "people/new"

        personDao.save(personMutable)
        return "redirect:/people"
    }

    @GetMapping("/{id}/edit")
    fun edit(
        @PathVariable("id") id: Int,
        model: Model
    ): String {
        model.addAttribute("person", personDao.show(id)?.mapToPersonMutable())
        return "people/edit"
    }

    @PatchMapping("/{id}")
    fun update(
        @ModelAttribute("person")
        @Valid
        personMutable: PersonMutable,

        bindingResult: BindingResult,

        @PathVariable("id")
        id: Int
    ): String {
        personValidator.validate(personMutable.mapToPerson(), bindingResult)

        if (bindingResult.hasErrors()) return "people/edit"

        personDao.update(id, personMutable)
        return "redirect:/people"
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): String {
        personDao.delete(id)
        return "redirect:/people"
    }
}