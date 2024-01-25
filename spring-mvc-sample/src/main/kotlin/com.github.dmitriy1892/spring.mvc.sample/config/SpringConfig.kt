package com.github.dmitriy1892.spring.mvc.sample.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring6.view.ThymeleafViewResolver

@Configuration
@ComponentScan("com.github.dmitriy1892.spring.mvc.sample")
@EnableWebMvc
open class SpringConfig @Autowired constructor(
    private val applicationContext: ApplicationContext
) : WebMvcConfigurer {

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        val resolver = ThymeleafViewResolver().apply { templateEngine = provideSpringTemplateEngine() }
        registry.viewResolver(resolver)
    }

    @Bean
    open fun provideSpringResourceTemplateResolver(): SpringResourceTemplateResolver {
        return SpringResourceTemplateResolver().apply {
            setApplicationContext(applicationContext)
            prefix = "/WEB-INF/views/"
            suffix = ".html"
        }
    }

    @Bean
    open fun provideSpringTemplateEngine(): SpringTemplateEngine {
        return SpringTemplateEngine().apply {
            setTemplateResolver(provideSpringResourceTemplateResolver())
            enableSpringELCompiler = true
        }
    }
}