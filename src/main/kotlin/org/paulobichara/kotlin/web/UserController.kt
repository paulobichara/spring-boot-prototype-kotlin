package org.paulobichara.kotlin.web

import org.paulobichara.kotlin.NewUserDto
import org.paulobichara.kotlin.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addNewUser(@Valid @RequestBody dto: NewUserDto) = userService.addNewUser(dto)

}