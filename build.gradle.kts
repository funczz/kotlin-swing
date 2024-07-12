/**
 * plugins
 */
plugins {
    kotlin("jvm") version "1.7.10" apply false
    id("nebula.release") version "15.3.1"
}

/**
 * plugin: nebula release, nebula maven-publish
 */
tasks {
    "release" {
        dependsOn(
            //":feature:publish", ":demo:publish"
            ":swing:publish"
        )
    }
}

/**
 * all projects
 */
allprojects {
    /**
     * build script
     */
    buildscript {
        /**
         * repositories
         */
        repositories {
            mavenLocal()
            mavenCentral()
        }
    }

    /**
     * repositories
     */
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

/**
 * sub projects
 */
subprojects {
    /**
     * plugins
     */
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "jacoco")

    /**
     * repositories
     */
    repositories {
        maven { setUrl("https://funczz.github.io/kotlin-junit5-cases") }
    }

    /**
     * dependencies
     */
    dependencies {
        /**
         * dependencies: libs Directory
         */
        "implementation"(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

        /**
         * dependencies: kotlin for JDK8
         */
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        "testImplementation"("org.junit.jupiter:junit-jupiter-engine:5.9.1")
        "testImplementation"("com.github.funczz:junit5-cases:0.1.0")
    }

    /**
     * task: JavaCompile
     */
    org.gradle.api.Action<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType(JavaCompile::class) {
        options.encoding = "UTF-8"
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }

    /**
     * task: KotlinCompile
     */
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    /**
     * task: Test
     */
    tasks.withType(Test::class.java) {
        useJUnitPlatform() //task: kotlintest-runner-junit5
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
