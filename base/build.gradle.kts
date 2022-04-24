import org.springframework.boot.gradle.plugin.SpringBootPlugin


// This is necessary to get Gradle use the plugin from its source instead of fetching it from the repository
// Don't use that in your project
buildscript {
    dependencies {
        classpath("com.github.node-gradle:gradle-node-plugin:3.0.0")
        classpath("org.apache.maven.plugins:maven-surefire-plugin:2.22.0")
        
    }
}

plugins {
    java
    war
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}



val tomcatVersion = "10.0.20"

repositories {
    mavenCentral()
   	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }

}

dependencies {

    // The basic project
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation(project(":angular"))
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")


    // The database
    implementation("org.postgresql:postgresql")
    implementation("org.hsqldb:hsqldb:2.3.4")
    implementation("org.hibernate:hibernate-core")
    implementation("org.hibernate.validator:hibernate-validator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")


    // The web
    implementation("org.springframework.boot:spring-boot-starter-web")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	
    // The tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.github.bonigarcia:webdrivermanager:4.3.1")
    testImplementation("org.seleniumhq.selenium:selenium-java")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.assertj:assertj-core")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    //The json
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")
}

tasks.test {
    useJUnitPlatform()
}
