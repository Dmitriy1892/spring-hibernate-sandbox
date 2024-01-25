import extensions.libs
import org.gradle.kotlin.dsl.war

plugins {
    id("spring-setup")
    id("hibernate-setup")
    war
}

dependencies {
    implementation(libs.thymeleaf.spring6)
    implementation(libs.hibernate.validator)
    implementation(libs.postgresql.driver)
}