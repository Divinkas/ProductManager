package com.divinkas.app.productmanager.ui

import androidx.lifecycle.ViewModel
import com.divinkas.app.productmanager.components.coroutine.CoroutineComponent
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel: ViewModel(), KoinComponent {
    protected val coroutineComponent: CoroutineComponent by inject()
}