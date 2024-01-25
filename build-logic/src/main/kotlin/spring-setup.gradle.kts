import extensions.libs

plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(kotlin("test"))

    implementation(libs.bundles.spring)
}