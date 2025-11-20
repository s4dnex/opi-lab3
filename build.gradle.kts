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
    implementation(
        libs.primefaces.get().group,
        libs.primefaces.get().name,
        libs.primefaces.get().version,
        classifier = "jakarta"
    )
    implementation(libs.bundles.hibernate)
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

tasks.jar {
    enabled = false
}

tasks.war {
    from("/src/webapp") {
        into("")
    }

    archiveFileName = "ROOT.war"
}