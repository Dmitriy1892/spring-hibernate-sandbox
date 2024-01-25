package com.github.dmitriy1892.spring.mvc.sample.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SecondController {

    @GetMapping("/exit")
    fun exit(): String = "second/exit"
}