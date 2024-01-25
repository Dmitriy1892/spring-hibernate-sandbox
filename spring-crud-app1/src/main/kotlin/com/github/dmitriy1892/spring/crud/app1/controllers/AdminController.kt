package com.github.dmitriy1892.spring.crud.app1.controllers

import com.github.dmitriy1892.spring.crud.app1.dao.PersonDao
import com.github.dmitriy1892.spring.crud.app1.models.PersonMutable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class AdminController @Autowired constructor(
    private val personDao: PersonDao
) {

    @GetMapping
    fun adminPage(
        model: Model,
        @ModelAttribute("person") personMutable: PersonMutable
    ): String {
        model.addAttribute("people", personDao.index())

        return "adminPage"
    }

    @PatchMapping("/add")
    fun makeAdmin(@ModelAttribute("person") personMutable: PersonMutable): String {
        println("Chosen admin id: ${personMutable.id}")

        return "redirect:/people"
    }
}