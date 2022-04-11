package com.vasilyevskii.rentteamtesttask.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilyevskii.rentteamtesttask.R
import com.vasilyevskii.rentteamtesttask.adapter.UserAdapter
import com.vasilyevskii.rentteamtesttask.databinding.FragmentMainBinding
import com.vasilyevskii.rentteamtesttask.model.UserDTO
import com.vasilyevskii.rentteamtesttask.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMain : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var userAdapter: UserAdapter
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadData()
        errorResponseApi()
    }

    private fun initRecyclerView(){
        userAdapter = UserAdapter()
        binding.recyclerViewFrMain.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    private fun loadData(){
        userViewModel.responseUsers.observe(requireActivity()) { response ->
            loadDataRecyclerView(response)
        }
    }

    private fun errorResponseApi(){
        userViewModel.responseError.observe(requireActivity()) { error ->
            Toast.makeText(activity, getString(R.string.error_response_api), Toast.LENGTH_LONG)
                .show()
            loadDatabase()
        }
    }

    private fun loadDataRecyclerView(userList: List<UserDTO>){
        visibleRecyclerView()
        userAdapter.submitList(userList)
    }

    private fun loadDatabase(){
        userViewModel.getAllUsersDatabase()
        userViewModel.responseUsers.observe(requireActivity()) { responseDatabase ->
            loadDataRecyclerView(responseDatabase)
        }
    }

    private fun visibleRecyclerView(){
        binding.progressBarMain.visibility = View.GONE
        binding.recyclerViewFrMain.visibility = View.VISIBLE
    }

}