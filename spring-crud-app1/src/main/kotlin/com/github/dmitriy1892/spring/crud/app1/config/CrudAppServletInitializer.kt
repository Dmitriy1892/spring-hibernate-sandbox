package com.github.dmitriy1892.spring.crud.app1.config

import jakarta.servlet.ServletContext
import jakarta.servlet.DispatcherType
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.filter.HiddenHttpMethodFilter
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import java.util.EnumSet

class CrudAppServletInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {

    override fun getRootConfigClasses(): Array<Class<*>>? = null

    override fun getServletConfigClasses(): Array<Class<*>> = arrayOf(SpringConfig::class.java)

    override fun getServletMappings(): Array<String> = arrayOf("/")

    override fun onStartup(servletContext: ServletContext) {
        super.onStartup(servletContext)
        registerCharacterEncodingFilter(servletContext)
        registerHiddenFieldFilter(servletContext)
    }

    private fun registerHiddenFieldFilter(servletContext: ServletContext) {
        servletContext
            .addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter())
            .addMappingForUrlPatterns(null, true, "/*")
    }

    private fun registerCharacterEncodingFilter(servletContext: ServletContext) {
        val dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD)

        val charEncodingFilter = CharacterEncodingFilter().apply {
            encoding = "UTF-8"
            setForceEncoding(true)
        }

        servletContext
            .addFilter("characterEncoding", charEncodingFilter)
            .addMappingForUrlPatterns(dispatcherTypes, true, "/*")
    }
}