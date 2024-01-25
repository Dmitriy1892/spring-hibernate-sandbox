package com.github.dmitriy1892.spring.crud.app1.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring6.view.ThymeleafViewResolver
import javax.sql.DataSource

@Configuration
@ComponentScan("com.github.dmitriy1892.spring.crud.app1")
@EnableWebMvc
@PropertySource("classpath:database.properties")
open class SpringConfig @Autowired constructor(
    private val applicationContext: ApplicationContext,
    private val environment: Environment
) : WebMvcConfigurer {

    @Bean
    open fun provideTemplateResolver(): SpringResourceTemplateResolver {
        return SpringResourceTemplateResolver().apply {
            setApplicationContext(applicationContext)
            prefix = "/WEB-INF/views/"
            suffix = ".html"
        }
    }

    @Bean
    open fun provideTemplateEngine(): SpringTemplateEngine {
        return SpringTemplateEngine().apply {
            setTemplateResolver(provideTemplateResolver())
            enableSpringELCompiler = true
        }
    }

    @Bean
    open fun provideDataSource(): DataSource {
        return DriverManagerDataSource().apply {
            setDriverClassName(requireNotNull(environment.getProperty("driver")))
            url = environment.getProperty("url")
            username = environment.getProperty("username")
            password = environment.getProperty("password")
        }
    }

    @Bean
    open fun provideJdbcTemplate(): JdbcTemplate = JdbcTemplate(provideDataSource())

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        val resolver = ThymeleafViewResolver().apply {
            templateEngine = provideTemplateEngine()
        }

        registry.viewResolver(resolver)
    }
}