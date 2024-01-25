package com.github.dmitriy1892.springcource.music.impl

import com.github.dmitriy1892.springcource.music.Music
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class ClassicalMusic : Music {

    override fun getSong(): String = "Hungarian Rhapsody"

    @PostConstruct
    fun initClassicalMusic() {
        println("ClassicalMusic initialization")
    }

    @PreDestroy
    fun destroyClassicalMusic() {
        println("ClassicalMusic destroying")
    }
}