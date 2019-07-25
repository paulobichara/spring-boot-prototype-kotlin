package org.paulobichara.kotlin.service

import org.paulobichara.kotlin.*
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.validation.Valid

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun addNewUser(@Valid dto: NewUserDto): User {
        if (userRepository.findByEmail(dto.email).isPresent) {
            throw UserAlreadyRegisteredException(dto.email)
        }

        val user = User(dto.email, passwordEncoder.encode(dto.password))
        user.roles.add(Role.ROLE_USER)

        return userRepository.save(user)
    }

}