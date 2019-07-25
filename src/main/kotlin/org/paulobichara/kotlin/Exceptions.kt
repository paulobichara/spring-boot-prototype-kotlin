package org.paulobichara.kotlin

import java.lang.RuntimeException

open class ApiException : RuntimeException {
    private val args: Array<Any>

    constructor(message: String): super(message) {
        args = arrayOf()
    }

    constructor(message: String, args: Array<Any>): super(message) {
        this.args = args
    }
}

class UserAlreadyRegisteredException(email: String) : ApiException("exception.alreadyRegistered.user", arrayOf(email))