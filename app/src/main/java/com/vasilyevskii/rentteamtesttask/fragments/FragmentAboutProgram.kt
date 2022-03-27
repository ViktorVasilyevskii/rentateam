package com.vasilyevskii.rentteamtesttask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vasilyevskii.rentteamtesttask.R
import com.vasilyevskii.rentteamtesttask.databinding.FragmentAboutProgramBinding
import com.vasilyevskii.rentteamtesttask.utils.Constants

class FragmentAboutProgram : Fragment(R.layout.fragment_about_program) {

    private lateinit var binding: FragmentAboutProgramBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutProgramBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadWebView()
    }


    private fun loadWebView(){
        binding.webViewAboutProgram.apply {
            settings.javaScriptEnabled = true
            loadUrl(Constants.URL_TEST_TASK)
        }
    }
}