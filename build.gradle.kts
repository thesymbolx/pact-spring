plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	id("org.springframework.boot") version "4.0.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.dale"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("tools.jackson.module:jackson-module-kotlin")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("au.com.dius.pact.provider:spring6:4.6.5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Test> {
	useJUnitPlatform()

	// Provide the version of this provider (e.g., 0.0.1-SNAPSHOT)
	systemProperty("pact.provider.version", project.version.toString())

	// Enable publishing results back to Pactflow
	systemProperty("pact.verifier.publishResults", "true")

	// Hardcode the branch to main for verification results
	systemProperty("pact.provider.branch", "main")

	// Ensure the token from your environment is accessible to the test
	systemProperty("PACT_BROKER_TOKEN", "k0xg2l3mRoGHrCo0vAPWUQ")
}
