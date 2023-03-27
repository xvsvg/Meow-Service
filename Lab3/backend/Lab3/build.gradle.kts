plugins {
    id("java")
}

group = "org.meows"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    implementation("jakarta.persistence:jakarta.persistence-api:3.0.0")


    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.2")
    implementation("org.springframework.boot:spring-boot-starter:3.0.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.0")
    implementation("org.postgresql:postgresql:42.5.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}