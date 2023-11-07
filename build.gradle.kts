plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation ("com.github.polygon-io:client-jvm:v5.1.0")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation ("com.fasterxml.jackson.core:jackson-databind")
    implementation ("jakarta.validation:jakarta.validation-api:3.0.0")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("com.google.code.gson:gson:2.10.1")



}

tasks.withType<Test> {
    useJUnitPlatform()
}
