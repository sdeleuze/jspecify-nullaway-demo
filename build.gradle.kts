import net.ltgt.gradle.errorprone.errorprone

plugins {
	id("java")
	alias(libs.plugins.errorprone)
	alias(libs.plugins.nullaway)
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
	implementation(libs.jspecify)
	errorprone(libs.errorprone.core)
	errorprone(libs.nullaway)
}
