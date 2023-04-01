plugins {
    id("java")
}

group = "org.myaukalki.console"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":Lab2:dao")))
    implementation(project(mapOf("path" to ":Lab2:services")))
    implementation(project(mapOf("path" to ":Lab2:services")))
    implementation(project(mapOf("path" to ":Lab2:dto")))
    implementation(project(mapOf("path" to ":Lab2:domain")))
    implementation(project(mapOf("path" to ":Lab2:domain")))
    implementation("org.jetbrains:annotations:23.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}