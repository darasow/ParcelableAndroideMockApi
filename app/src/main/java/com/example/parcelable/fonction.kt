package com.example.parcelable

import UserEndpoint

fun checkUserExistence(users: List<UserEndpoint>?, loginText: String, passwordText: String): Boolean {
    users?.forEach { user ->
        val userName = user.name?.toString() ?: ""
        val userPassword = user.password?.toString() ?: ""
        if (loginText == userName && passwordText == userPassword) {
            return true
        }
    }
    return false
}