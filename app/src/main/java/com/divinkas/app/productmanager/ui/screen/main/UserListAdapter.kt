package com.divinkas.app.productmanager.ui.screen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.divinkas.app.productmanager.R
import com.divinkas.app.productmanager.bean.backend.User
import com.divinkas.app.productmanager.databinding.ItemUserBinding

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    private var users: MutableList<User> = ArrayList()
    var onUserClick: (user: User) -> Unit = {}

    fun addUsers(list: List<User>) {
        for (item in list) {
            if (users.find { it.id == item.id } == null) {
                users.add(item)
                notifyItemInserted(users.indexOf(item))
            }
        }
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.binding.user = users[position]
        holder.binding.itemContainer.setOnClickListener {
            onUserClick(users[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserListViewHolder(DataBindingUtil.bind(view)!!)
    }

    override fun getItemCount() = users.size

    class UserListViewHolder(
            val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)
}