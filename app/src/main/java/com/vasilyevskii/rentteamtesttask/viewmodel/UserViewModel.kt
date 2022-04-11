package com.vasilyevskii.rentteamtesttask.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vasilyevskii.rentteamtesttask.model.UserDTO
import com.vasilyevskii.rentteamtesttask.repository.UserDataBaseRepository
import com.vasilyevskii.rentteamtesttask.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
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

    init {
        getResponseUsers()
    }

    private fun getResponseUsers() {

        compositeDisposable.add(
            userRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _responseUsers.postValue(result.data)
                    updateUserDatabase(result.data)
                },
                    { ex ->
                        _responseError.postValue(ex.message)
                    })
        )
    }

    @SuppressLint("CheckResult")
    private fun updateUserDatabase(userDTOList: List<UserDTO>) {
            userDTOList.forEach { user ->
                userDataBaseRepository.insertUser(user)
                    .subscribeOn(Schedulers.computation())
                    .subscribe()
            }
    }

    fun getAllUsersDatabase() {
        userDataBaseRepository.getAllUsersList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: MaybeObserver<List<UserDTO>>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(listUser: List<UserDTO>) {
                    _responseUsers.postValue(listUser)
                }

                override fun onError(e: Throwable) {
                    _responseError.postValue(e.message)
                }

                override fun onComplete() {
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

