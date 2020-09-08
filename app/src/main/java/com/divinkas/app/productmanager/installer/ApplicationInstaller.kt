package com.divinkas.app.productmanager.installer

import com.divinkas.app.productmanager.ProductManagerApplication

interface ApplicationInstaller {
    fun install(application: ProductManagerApplication)
}