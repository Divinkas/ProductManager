package com.divinkas.app.productmanager.ui.screen.main

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.divinkas.app.productmanager.R
import com.divinkas.app.productmanager.bean.backend.User
import com.divinkas.app.productmanager.helper.livedata.LoadingViewState
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.divinkas.app.productmanager.ui.screen.BaseViewModelScreen
import com.divinkas.app.productmanager.ui.utils.*
import kotlinx.android.synthetic.main.screen_user_list.*

class UserListScreen : BaseViewModelScreen<UsersViewModel>(R.layout.screen_user_list) {
    override val viewModel: UsersViewModel by viewModel()

    private lateinit var scrolledHelper: ScrolledHelper
    private val adapter = UserListAdapter()

    override fun setupUi() {
        showProgress()
        adapter.onUserClick = {
            goToUserDetailScreen(it)
        }

        scrolledHelper = ScrolledHelper(false, object : ScrolledHelper.OnScrollCallback {
            override fun onScrolledToEnd() {
                viewModel.loadUsers()
            }
        })

        rv_user_list.layoutManager = LinearLayoutManager(context!!)
        rv_user_list.addOnScrollListener(scrolledHelper)
        rv_user_list.adapter = adapter

        viewModel.loadUsers()
    }

    override fun setupLiveDataObservers() {
        observeViewState(viewModel.usersLiveData) {
            when (it) {
                is LoadingViewState.Success -> {
                    hideProgress()
                    adapter.addUsers(it.data)
                    scrolledHelper.setLastPage(it.data.size < viewModel.getLimitItemsOnPage())
                    scrolledHelper.loadMore(false)
                }
                is LoadingViewState.Fail -> {
                    showRectangleToast(context!!, R.string.something_went_wrong)
                }
            }
        }
    }

    private fun goToUserDetailScreen(user: User) {
        findNavController().navigate(UserListScreenDirections.toUserDetailScreen(user))
    }

    private fun showProgress() {
        progress.visible()
    }

    private fun hideProgress() {
        progress.gone()
    }
}