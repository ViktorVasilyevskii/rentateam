package com.vasilyevskii.rentteamtesttask.adapter

import androidx.recyclerview.widget.DiffUtil
import com.vasilyevskii.rentteamtesttask.model.UserDTO

class UserItemDiffCallback: DiffUtil.ItemCallback<UserDTO>() {

    override fun areItemsTheSame(oldItem: UserDTO, newItem: UserDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserDTO, newItem: UserDTO): Boolean {
        return oldItem == newItem
    }
}