package com.vasilyevskii.rentteamtesttask.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.vasilyevskii.rentteamtesttask.databinding.ActivityCardUserBinding
import com.vasilyevskii.rentteamtesttask.utils.Constants

class ActivityCardUser : AppCompatActivity() {

    private lateinit var binding: ActivityCardUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDataPutExtra()

    }

    private fun loadDataPutExtra(){
        val firstName = intent.getStringExtra(Constants.FIRST_NAME)
        val lastName = intent.getStringExtra(Constants.LAST_NAME)
        val email = intent.getStringExtra(Constants.EMAIL)
        val avatar = intent.getStringExtra(Constants.AVATAR)
        displayDataScreen(firstName!!, lastName!!,email!!,avatar!!)
    }

    private fun displayDataScreen(firstName: String, lastName: String, email: String, avatar: String){
        binding.firstNameItem.text = firstName
        binding.lastNameItem.text = lastName
        binding.EmailItem.text = email
        Picasso.get()
            .load(avatar)
            .into(binding.imageViewAvatar)
    }
}