package com.divinkas.app.productmanager.helper.livedata

sealed class ViewState<T> {
    data class Data<T>(val data: T) : ViewState<T>()

    class NoneObservable<T> : ViewState<T>() {
        override fun toString(): String {
            return javaClass.simpleName
        }
    }
}
