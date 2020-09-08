package com.divinkas.app.productmanager.bean.base

sealed class ProductManagerError {
    class Unknown(val message: String = "") : ProductManagerError()
    class Backend(val message: String = "") : ProductManagerError()
}