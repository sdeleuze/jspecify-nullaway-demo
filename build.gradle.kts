import net.ltgt.gradle.errorprone.errorprone

plugins {
	id("java")
	id("net.ltgt.errorprone") version "5.1.0" // https://github.com/tbroyer/gradle-errorprone-plugin
	id("net.ltgt.nullaway") version "3.0.0" // https://github.com/tbroyer/gradle-nullaway-plugin
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

nullaway {
	onlyNullMarked = true
	jspecifyMode = true
}

tasks.withType<JavaCompile> {
	options.errorprone  {
		disableAllChecks = true // Other error prone checks are disabled
		error("RequireExplicitNullMarking") // Require @NullMarked or @NullUnmarked on everything
		nullaway {
			error()
		}
	}
	// Keep a JDK 17 baseline
	options.release = 17
}

dependencies {
	implementation("org.jspecify:jspecify:1.0.0") // https://jspecify.dev/
	errorprone("com.google.errorprone:error_prone_core:2.42.0") // https://github.com/google/error-prone
	errorprone("com.uber.nullaway:nullaway:0.13.2") // https://github.com/uber/NullAway
}
