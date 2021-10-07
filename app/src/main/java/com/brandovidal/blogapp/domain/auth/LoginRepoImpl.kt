package com.brandovidal.blogapp.domain.auth

import com.brandovidal.blogapp.data.remote.auth.LoginDataSource
import com.google.firebase.auth.FirebaseUser

class LoginRepoImpl(private val dataSource: LoginDataSource): LoginRepo {

    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email, password)
}