plugins {
    id("java")
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
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
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework:spring-websocket")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("io.projectreactor.tools:blockhound:1.0.6.RELEASE")
    implementation("io.projectreactor:reactor-tools")
    implementation("org.clojure:clojure:1.11.1")
    implementation("org.msync:spring-boost:0.2.0-SNAPSHOT")
    implementation("compojure:compojure:1.6.3")
}

tasks.register<Copy>("copyClojure") {
    from("src/main/clojure")
    into("build/classes/java/main")
}

tasks.getByName("bootRunMainClassName").dependsOn("copyClojure")
