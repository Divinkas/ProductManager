package com.divinkas.app.productmanager.helper.livedata

class MutableViewStateLiveData<T> : ViewStateLiveData<T>() {
    public override fun setState(state: ViewState<T>) {
        super.setState(state)
    }

    public override fun postState(state: ViewState<T>) {
        super.postState(state)
    }
}
