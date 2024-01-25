plugins {
    id("hibernate-setup")
}

group = "com.github.dmitriy1892.hibernate.app"
version = libs.versions.app.version.get()

dependencies {
    implementation(project(":utils"))
}