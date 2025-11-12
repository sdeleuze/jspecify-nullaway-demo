plugins {
    id("java")
	id("io.spring.nullability") version "0.0.8"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
	toolchain {
		// Usage of a recent javac version for compatibility with NullAway JSpecify mode
        languageVersion = JavaLanguageVersion.of(25)
	}
}

tasks.withType<JavaCompile> {
	// Keep a JDK 17 baseline
	options.release = 17
}

dependencies {
	// https://jspecify.dev/
    implementation("org.jspecify:jspecify:1.0.0")
}
