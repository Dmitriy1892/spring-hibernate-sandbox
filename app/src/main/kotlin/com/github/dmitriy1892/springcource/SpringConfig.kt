package com.github.dmitriy1892.springcource

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@ComponentScan("com.github.dmitriy1892.springcource")
@PropertySource("classpath:musicPlayer.properties")
open class SpringConfig