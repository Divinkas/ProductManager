package com.divinkas.app.productmanager.ui.screen

import com.divinkas.app.productmanager.ui.BaseViewModel

abstract class BaseViewModelScreen<VM : BaseViewModel>(layoutRes: Int) : BaseScreen(layoutRes) {
    protected abstract val viewModel: VM
}
