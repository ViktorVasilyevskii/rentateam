package com.vasilyevskii.rentteamtesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vasilyevskii.rentteamtesttask.databinding.ActivityMainBinding
import com.vasilyevskii.rentteamtesttask.screen.FragmentAboutProgram
import com.vasilyevskii.rentteamtesttask.screen.FragmentMain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.navView.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment = FragmentMain()
            when(item.itemId){
                R.id.main_fragment -> fragment = FragmentMain()
                R.id.favorite -> fragment = FragmentAboutProgram()
            }
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainerView.id, fragment)
                .commit()
            return@setOnNavigationItemSelectedListener true
        }

    }

}