package com.github.dmitriy1892.springcource

import com.github.dmitriy1892.springcource.music.Music
import org.springframework.beans.factory.getBean
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    val context = AnnotationConfigApplicationContext(SpringConfig::class.java)

    val player1 = context.getBean<MusicPlayer>("musicPlayer")

    player1.playMusic()

    println(player1.name)

    println(player1.volume)

    println("=============")

    val classicalMusic1 = context.getBean<Music>("classicalMusic")
    val classicalMusic2 = context.getBean<Music>("classicalMusic")
    println("$classicalMusic1")
    println("$classicalMusic2")

    context.close()
}