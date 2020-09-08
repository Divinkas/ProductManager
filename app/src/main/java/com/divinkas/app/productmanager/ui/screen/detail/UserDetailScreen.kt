package com.divinkas.app.productmanager.ui.screen.detail

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.divinkas.app.productmanager.R
import com.divinkas.app.productmanager.databinding.ScreenUserDetailBinding
import com.divinkas.app.productmanager.ui.screen.BaseViewModelScreen
import com.divinkas.app.productmanager.ui.screen.main.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailScreen: BaseViewModelScreen<UsersViewModel>(R.layout.screen_user_detail) {
    override val viewModel: UsersViewModel by viewModel()

    private val args: UserDetailScreenArgs by navArgs()
    private lateinit var binding: ScreenUserDetailBinding

    override fun setupDataBinding(view: View) {
        binding = DataBindingUtil.bind(view)!!
    }

    override fun setupUi() {
        binding.user = args.user
    }
}