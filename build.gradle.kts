import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.hibernate.orm") version "6.4.4.Final"
	id("org.graalvm.buildtools.native") version "0.9.28"
}

group = "com.malibentoo"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// DBS
	implementation("com.zaxxer:HikariCP:5.1.0")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// Web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Dev
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events(
			TestLogEvent.PASSED,
			TestLogEvent.FAILED,
			TestLogEvent.SKIPPED,
		)
		exceptionFormat = TestExceptionFormat.FULL
		showExceptions = true
		showCauses = true
	}
}

hibernate {
	enhancement {
		enableAssociationManagement.set(true)
	}
}

