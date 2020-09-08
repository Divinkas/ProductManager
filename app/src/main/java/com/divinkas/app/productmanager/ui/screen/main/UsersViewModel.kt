package com.divinkas.app.productmanager.ui.screen.main

import com.divinkas.app.productmanager.bean.backend.User
import com.divinkas.app.productmanager.bean.base.ProductManagerResult
import com.divinkas.app.productmanager.components.network.user.UserComponent
import com.divinkas.app.productmanager.helper.livedata.LoadingViewState
import com.divinkas.app.productmanager.helper.livedata.LoadingViewStateLiveData
import com.divinkas.app.productmanager.helper.livedata.MutableLoadingViewStateLiveData
import com.divinkas.app.productmanager.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.inject

class UsersViewModel: BaseViewModel() {
    private val userComponent: UserComponent by inject()

    private val DEFAULT_LIMIT_ON_PAGE = 6
    private var page: Int = 1

    private val _usersLiveData: MutableLoadingViewStateLiveData<List<User>> = MutableLoadingViewStateLiveData()
    val usersLiveData: LoadingViewStateLiveData<List<User>> = _usersLiveData

    fun loadUsers() {
        coroutineComponent.launchOnMain {
            _usersLiveData.setState(LoadingViewState.Loading())
            when (val result = getUsers(page)) {
                is ProductManagerResult.Success -> {
                    _usersLiveData.setState(LoadingViewState.Success(result.value.userList))
                    page++
                }
                else -> _usersLiveData.setState(LoadingViewState.Fail())
            }
        }
    }

    fun getLimitItemsOnPage() = DEFAULT_LIMIT_ON_PAGE

    private suspend fun getUsers(page: Int) = withContext(Dispatchers.Default) {
        userComponent.getUsers(page)
    }
}