package com.github.dmitriy1892.spring.crud.app1.controllers

import com.github.dmitriy1892.spring.crud.app1.dao.PersonDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/test-batch-update")
class BatchController @Autowired constructor(
    private val personDao: PersonDao
) {

    @GetMapping
    fun index(): String {
        return "batch/index"
    }

    @GetMapping("/without")
    fun withoutBatch(): String {
        personDao.testMultipleUpdate()

        return "redirect:/people"
    }

    @GetMapping("/with")
    fun withBatch(): String {
        personDao.testBatchUpdate()
        return "redirect:/people"
    }
}