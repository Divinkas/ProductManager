package com.divinkas.app.productmanager.installer

import com.divinkas.app.productmanager.ProductManagerApplication
import timber.log.Timber

object TimberInstaller : ApplicationInstaller {
    override fun install(application: ProductManagerApplication) {
        Timber.plant(Timber.DebugTree())
    }
}
