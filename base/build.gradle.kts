import org.springframework.boot.gradle.plugin.SpringBootPlugin


// This is necessary to get Gradle use the plugin from its source instead of fetching it from the repository
// Don't use that in your project
buildscript {
    dependencies {
        classpath("com.github.node-gradle:gradle-node-plugin:3.0.0")
    }
}

plugins {
    java
    war
    id("org.springframework.boot") version "2.6.6"
    
}


repositories {
    mavenCentral()
}

dependencies {

    // The basic project
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation(project(":angular"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    

    // The security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // The database
    implementation("org.postgresql:postgresql")
    implementation("org.hsqldb:hsqldb:2.3.4")
    implementation("org.hibernate.validator:hibernate-validator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // The web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // The tools
    compileOnly("org.springframework.boot:spring-boot-devtools")

    // The tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.github.bonigarcia:webdrivermanager:4.3.1")
    testImplementation("org.seleniumhq.selenium:selenium-java")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.assertj:assertj-core")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

}

tasks.test {
    useJUnitPlatform()
}
