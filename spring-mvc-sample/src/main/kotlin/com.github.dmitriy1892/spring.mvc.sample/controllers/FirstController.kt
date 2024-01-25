package com.github.dmitriy1892.spring.mvc.sample.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class FirstController {

    @GetMapping("/hello")
    fun helloPage(
        @RequestParam(value = "name", required = false) name: String?,
        @RequestParam(value = "surname", required = false) surname: String?,
        model: Model
    ): String {
        model.addAttribute("message", "Hello, $name $surname")

        println("Hello, $name $surname")

        return "first/hello"
    }

    @GetMapping("/goodbye")
    fun goodbyePage(): String {
        return "first/goodbye"
    }
}