package com.divinkas.app.productmanager.components.network.user

import com.divinkas.app.productmanager.bean.backend.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserWebService {

    @GET("/api/users")
    fun getUsers(@Query("page") page:Int): UserResponse
}