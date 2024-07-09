/**
 * plugins
 */
plugins {
    application
}

/**
 * plugin: application
 */
application {
    mainClass.set(System.getProperty("mainClass") ?: "")
}

/**
 * dependencies
 */
dependencies {
    implementation(project(":swing"))
}
