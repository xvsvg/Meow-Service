plugins {
    id("java")
}

group = "org.myaukalki"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":Lab2:domain"))
    implementation(project(":Lab2:dao"))
    implementation(project(":Lab2:dto"))
    implementation(project(mapOf("path" to ":Lab2:Console")))
    testImplementation(project(mapOf("path" to ":Lab2:services")))
    implementation("org.hibernate:hibernate-gradle-plugin:5.6.15.Final")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.mockito:mockito-core:2.1.0")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}