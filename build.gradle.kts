buildscript {
    repositories {
        mavenLocal()
        jcenter()
    }
}

tasks.existing(Wrapper::class) {
    gradleVersion = "4.10.2"
    distributionType = Wrapper.DistributionType.ALL
}

description = "AppSmart"
group = "com.AppSmart"
version = version

plugins {
    java
    id("io.qameta.allure") version "2.6.0"
    id("io.freefair.lombok") version "5.3.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val allureVersion = "2.9.0"

allure {

    version = allureVersion
    useTestNG {
        version = allureVersion
    }
    downloadLink = "https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/$allureVersion/allure-commandline-$allureVersion.zip"
}

dependencies {
    implementation("org.testng:testng:6.14.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.12.3")
    implementation("com.codeborne:selenide:6.2.1")
    implementation("net.andreinc:mockneat:0.4.8")
    implementation("com.konghq:unirest-java:4.0.0-RC2")
    implementation("io.rest-assured:rest-assured:4.5.0")
    implementation("org.hamcrest:hamcrest-all:1.3")
    implementation("org.apache.logging.log4j:log4j-core:2.17.1")
    testImplementation("org.slf4j:slf4j-simple:2.0.0-alpha6")
}

tasks.named<Test>("test") {
    useTestNG(closureOf<TestNGOptions> {
        suites("src/test/resources/testng.xml")
    })
}

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
}

repositories {
    mavenLocal()
    jcenter()
}