import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    kotlin("plugin.allopen") version "1.8.22"
}

allOpen {
    annotation("javax.persistence.Entity")
}

group = "com.vmaier"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.0")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.0.0")
    implementation(platform("com.amazonaws:aws-java-sdk-bom:1.12.497"))
    implementation("com.amazonaws:aws-java-sdk-s3")
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.flywaydb:flyway-core:9.19.4")
    runtimeOnly("org.postgresql:postgresql")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
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

tasks.register<Copy>("copyJarToDockerDir") {
    from(layout.buildDirectory.dir("libs"))
    include("${project.name}-${project.version}.jar")
    into("docker")
}

tasks.named("copyJarToDockerDir") {
    dependsOn(":build")
}
