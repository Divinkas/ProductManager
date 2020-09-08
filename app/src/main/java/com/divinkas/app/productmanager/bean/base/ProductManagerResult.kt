package com.divinkas.app.productmanager.bean.base


sealed class ProductManagerResult<out T> {
    data class Error(val error: ProductManagerError) : ProductManagerResult<Nothing>()
    data class Success<T>(val value: T) : ProductManagerResult<T>()
}
