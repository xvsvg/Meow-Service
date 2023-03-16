plugins {
    id("java")
}

group = "org.itmotech"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:42.1.4")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}