package com.divinkas.app.productmanager

import android.app.Application
import com.divinkas.app.productmanager.installer.KoinInstaller
import com.divinkas.app.productmanager.installer.TimberInstaller

class ProductManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TimberInstaller.install(this)
        KoinInstaller.install(this)
    }
}