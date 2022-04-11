package com.vasilyevskii.rentteamtesttask.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vasilyevskii.rentteamtesttask.databinding.UserItemAdapterBinding
import com.vasilyevskii.rentteamtesttask.screen.ActivityCardUser
import com.vasilyevskii.rentteamtesttask.model.UserDTO
import com.vasilyevskii.rentteamtesttask.utils.Constants

class UserAdapter : ListAdapter<UserDTO, UserAdapter.UserViewHolder>(UserItemDiffCallback()) {

    inner class UserViewHolder(
        val binding: UserItemAdapterBinding
    ) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userItem = getItem(position)

        holder.binding.apply {
            firstName.text = userItem.first_name
            lastName.text = userItem.last_name
            this.root.setOnClickListener {
                val intent = Intent(it.context, ActivityCardUser::class.java)
                intent.putExtra(Constants.FIRST_NAME, userItem.first_name)
                intent.putExtra(Constants.LAST_NAME, userItem.last_name)
                intent.putExtra(Constants.EMAIL, userItem.email)
                intent.putExtra(Constants.AVATAR, userItem.avatar)
                this.root.context.startActivity(intent)
            }
        }
    }


}