package com.divinkas.app.productmanager.components.network.user

import com.divinkas.app.productmanager.bean.backend.UserResponse
import com.divinkas.app.productmanager.bean.base.ProductManagerResult
import com.divinkas.app.productmanager.components.network.HttpCommunicationComponent

class BaseUserComponent(
    private val httpCommunicationComponent: HttpCommunicationComponent
): UserComponent {
    override suspend fun getUsers(page: Int): ProductManagerResult<UserResponse> {
        val webService = httpCommunicationComponent.createService(UserWebService::class.java)
        return httpCommunicationComponent.execute { webService.getUsers(page) }
    }
}