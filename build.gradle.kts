plugins {
    java
    war
    alias(libs.plugins.shadow)
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.bundles.jakarta)
    implementation(libs.gson)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.war {
    from("/src/webapp") {
        into("")
    }
}