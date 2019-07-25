package org.paulobichara.kotlin

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

enum class Role {
    ROLE_USER, ROLE_ADMIN
}

@Entity
@Table(schema = "prototype")
data class User(
    @Column(nullable = false)
    @get: Email(message = "{validation.email.notValid}")
    @get: NotBlank(message = "{validation.email.notBlank}")
    val email: String = "",

    @Column(nullable = false)
    @JsonIgnore
    @get: Length(min = 8, message = "{validation.password.notValid}")
    @get: NotBlank(message = "{validation.password.notBlank}")
    val password: String = ""
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(schema = "prototype", name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    val roles = mutableSetOf<Role>()
}