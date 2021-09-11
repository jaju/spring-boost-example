plugins {
    id("java")
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

sourceSets {
    main {
        java {
            srcDir("src/main/clojure")
        }
    }
}

repositories {
    mavenLocal()
    maven {
        name = "Clojars"
        url = uri("https://clojars.org/repo")
    }
    // When using SNAPSHOT version of spring-boost
    maven {
        name = "Sonatype Snapshots"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        mavenContent {
            snapshotsOnly()
        }
    }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework:spring-websocket")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("io.projectreactor.tools:blockhound:1.0.6.RELEASE")
    implementation("io.projectreactor:reactor-tools")
    implementation("org.clojure:clojure:1.10.3")
    implementation("org.msync:spring-boost:0.2.0-SNAPSHOT")
    implementation("compojure:compojure:1.6.2")
}

tasks.register<Copy>("copyClojure") {
    into("build/classes/java/main") {
        destinationDir = file(".")
        from("src/main/clojure")
    }
}

tasks.getByName("bootRun").dependsOn("copyClojure")
tasks.getByName("bootJar").dependsOn("copyClojure")
