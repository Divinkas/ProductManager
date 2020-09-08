package com.divinkas.app.productmanager.components.network.user

import com.divinkas.app.productmanager.bean.backend.UserResponse
import com.divinkas.app.productmanager.bean.base.ProductManagerResult

interface UserComponent {
    suspend fun getUsers(page: Int): ProductManagerResult<UserResponse>
}