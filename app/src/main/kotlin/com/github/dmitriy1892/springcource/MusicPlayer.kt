package com.github.dmitriy1892.springcource

import com.github.dmitriy1892.springcource.music.Music
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MusicPlayer @Autowired constructor(
    private var musicList: List<Music>,
    @Qualifier("classicalMusic") private val music: Music
) {

    @Value("\${musicPlayer.name}")
    lateinit var name: String

    @Value("\${musicPlayer.volume}")
    var volume: String? = null

    fun playMusic() {
        musicList.forEach { music ->
            println("Playing: ${music.getSong()}")
        }
        println("Playing single song: ${music.getSong()}")
    }
}