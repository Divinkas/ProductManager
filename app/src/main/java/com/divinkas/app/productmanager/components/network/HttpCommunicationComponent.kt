package com.divinkas.app.productmanager.components.network

import com.divinkas.app.productmanager.bean.base.ProductManagerResult

interface HttpCommunicationComponent {
    fun getAcceptHeader(): String
    fun <T> createService(clazz: Class<T>): T
    suspend fun <T> execute(request: suspend () -> Any?): ProductManagerResult<T>
}