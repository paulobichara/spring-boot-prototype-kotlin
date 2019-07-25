package org.paulobichara.kotlin

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.util.*

@Configuration
class MessageSourceConfig {
    @Bean
    fun messageSource() : MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    @Bean
    fun validatorFactory() : LocalValidatorFactoryBean {
        val validatorFactory = LocalValidatorFactoryBean()
        validatorFactory.setValidationMessageSource(messageSource())
        return validatorFactory
    }
}

@Configuration
class SecurityConfig {
    @Bean
    fun encoder() : PasswordEncoder = BCryptPasswordEncoder()
}

@Configuration
class SwaggerConfig {
    @Value("\${api.swagger - ui.enabled:false}")
    private var enabled: Boolean = false

    @Bean
    fun swaggerDocket() : Docket = Docket(DocumentationType.SWAGGER_2)
            .enable(enabled)
            .pathMapping("/")
            .apiInfo(ApiInfo.DEFAULT)
            .forCodeGeneration(true)
            .genericModelSubstitutes(ResponseEntity::class.java)
            .ignoredParameterTypes(Pageable::class.java)
            .ignoredParameterTypes(java.sql.Date::class.java)
            .directModelSubstitute(java.time.LocalDate::class.java, java.sql.Date::class.java)
            .directModelSubstitute(java.time.ZonedDateTime::class.java, Date::class.java)
            .directModelSubstitute(java.time.LocalDateTime::class.java, Date::class.java)
            .useDefaultResponseMessages(false)
            .select().paths(PathSelectors.any())
            .build()
}