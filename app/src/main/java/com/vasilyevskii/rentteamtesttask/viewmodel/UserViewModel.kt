package com.vasilyevskii.rentteamtesttask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vasilyevskii.rentteamtesttask.model.UserDTO
import com.vasilyevskii.rentteamtesttask.repository.UserDataBaseRepository
import com.vasilyevskii.rentteamtesttask.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.MaybeObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class UserViewModel
@Inject constructor(
    private val userRepository: UserRepository,
    private val userDataBaseRepository: UserDataBaseRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _responseUsers = MutableLiveData<List<UserDTO>>()
    val responseUsers: LiveData<List<UserDTO>> = _responseUsers

    private val _responseError = MutableLiveData<String>()
    val responseError: LiveData<String> = _responseError

    private val _responseUsersDatabase = MutableLiveData<List<UserDTO>>()
    val responseUsersDatabase: LiveData<List<UserDTO>> = _responseUsersDatabase

    init {
        getResponseUsers()
    }

    private fun getResponseUsers() {

        compositeDisposable.add(
            userRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ result ->
                    _responseUsers.postValue(result.data)
                    updateUserDatabase(result.data)
                },
                    { ex ->
                        _responseError.postValue(ex.message)
                    })
        )
    }

    private fun updateUserDatabase(userDTOList: List<UserDTO>) {
        Thread{
            userDTOList.forEach { user ->
                Log.d("updateUserDatabase", "updateUserDatabase == $user")
                userDataBaseRepository.insertUser(user)
            }
        Thread.sleep(2000)
        }.start()
    }

    fun getAllUsersDatabase() {
        userDataBaseRepository.getAllUsersList()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object: MaybeObserver<List<UserDTO>>{
                override fun onSubscribe(d: Disposable) {
                    Log.d("onSubscribe", "onSubscribe == $d")
                }

                override fun onSuccess(listUser: List<UserDTO>) {
                    _responseUsersDatabase.postValue(listUser)
                    Log.d("onSuccess", "onSuccess == $listUser")
                }

                override fun onError(e: Throwable) {
                    Log.d("onError", "onError == ${e.printStackTrace()}")
                }

                override fun onComplete() {
                    Log.d("onComplete", "onComplete =============== start")
                }

            })
    }


    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
        super.onCleared()
    }

}

