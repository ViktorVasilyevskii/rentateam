package com.vasilyevskii.rentteamtesttask.di

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vasilyevskii.rentteamtesttask.MainActivity
import com.vasilyevskii.rentteamtesttask.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainNavigationModule {

    @Provides
    fun provideNavController(activity: MainActivity): NavController{
        return activity.findNavController(R.id.nav_view)
    }
}