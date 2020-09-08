package com.divinkas.app.productmanager.helper.livedata

sealed class LoadingViewState<T> {
    class NoneObservable<T> : LoadingViewState<T>()
    class Fail<T> : LoadingViewState<T>()
    class Success<T>(val data: T) : LoadingViewState<T>()
    class Loading<T> : LoadingViewState<T>()
}
