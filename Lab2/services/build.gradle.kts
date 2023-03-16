plugins {
    id("java")
}

group = "org.myaukalki.services"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":Lab2:dao"))
    implementation(project(":Lab2:domain"))
    implementation("javax.persistence:javax.persistence-api:2.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}