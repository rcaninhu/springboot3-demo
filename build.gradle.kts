import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.3"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.owasp.dependencycheck") version "7.3.2"
    id("com.bmuschko.docker-spring-boot-application") version "9.2.1"
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.spring") version "1.8.0"
    kotlin("plugin.jpa") version "1.8.0"
}

group = "net.rcan"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // openapi - swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("com.h2database:h2")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named("bootJar") {
    enabled = false
}


docker {
    springBootApplication {
        baseImage.set("gradle:jdk17")
        ports.set(listOf())
        images.set(listOf("inhu00/sample-springboot3-kotlin:0.0.1","inhu00/sample-springboot3-kotlin:latest"))
    }
}
