package com.divinkas.app.productmanager.installer

import com.divinkas.app.productmanager.ProductManagerApplication
import com.divinkas.app.productmanager.components.coroutine.BaseCoroutineComponent
import com.divinkas.app.productmanager.components.coroutine.CoroutineComponent
import com.divinkas.app.productmanager.components.network.BaseHttpCommunicationComponent
import com.divinkas.app.productmanager.components.network.HttpCommunicationComponent
import com.divinkas.app.productmanager.components.network.user.BaseUserComponent
import com.divinkas.app.productmanager.components.network.user.UserComponent
import com.divinkas.app.productmanager.ui.screen.main.UsersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object KoinInstaller : ApplicationInstaller {
    private val appModule = module {
        single<HttpCommunicationComponent> { BaseHttpCommunicationComponent() }
        single<UserComponent> { BaseUserComponent(get()) }
        factory<CoroutineComponent> { BaseCoroutineComponent() }
    }

    private val viewModelModule = module {
        viewModel { UsersViewModel() }
    }

    override fun install(application: ProductManagerApplication) {
        startKoin {
            androidLogger()
            androidContext(application)
            modules(appModule)
            modules(viewModelModule)
        }
    }
}