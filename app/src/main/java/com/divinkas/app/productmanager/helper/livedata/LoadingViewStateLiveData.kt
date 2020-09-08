package com.divinkas.app.productmanager.helper.livedata

import androidx.lifecycle.LiveData

open class LoadingViewStateLiveData<T> : LiveData<LoadingViewState<T>>() {
    protected open fun setState(state: LoadingViewState<T>) {
        value = state
    }

    protected open fun postState(state: LoadingViewState<T>) {
        postValue(state)
    }
}
