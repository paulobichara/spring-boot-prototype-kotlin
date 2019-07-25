rootProject.name = "spring-boot-prototype-kotlin"

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.version == null) {
                if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                    gradle.rootProject.extra["kotlinVersion"]?.let { useVersion(it as String) }
                } else if (requested.id.id == "org.springframework.boot") {
                    gradle.rootProject.extra["springBootVersion"]?.let { useVersion(it as String) }
                } else if (requested.id.id == "io.spring.dependency-management") {
                    gradle.rootProject.extra["springDependencyVersion"]?.let { useVersion(it as String) }
                }
            }
        }
    }
}