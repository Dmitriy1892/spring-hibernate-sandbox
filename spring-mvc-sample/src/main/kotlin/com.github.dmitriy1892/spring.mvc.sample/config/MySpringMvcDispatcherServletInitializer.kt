package com.github.dmitriy1892.spring.mvc.sample.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class MySpringMvcDispatcherServletInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {

    override fun getRootConfigClasses(): Array<Class<*>>? = null

    override fun getServletConfigClasses(): Array<Class<*>> = arrayOf(SpringConfig::class.java)

    override fun getServletMappings(): Array<String> = arrayOf("/")
}