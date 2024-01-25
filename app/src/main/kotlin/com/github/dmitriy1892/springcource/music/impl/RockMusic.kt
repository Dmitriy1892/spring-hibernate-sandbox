package com.github.dmitriy1892.springcource.music.impl

import com.github.dmitriy1892.springcource.music.Music
import org.springframework.stereotype.Component

@Component
class RockMusic : Music {

    override fun getSong(): String = "Wind cries Mary"
}