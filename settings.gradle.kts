pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "spring-app1"

includeBuild("build-logic")

include("app")
include(":spring-mvc-sample")
include(":spring-crud-app1")
include(":hibernate-app1")
include(":hibernate-app2")
include(":hibernate-app3")
include(":utils")