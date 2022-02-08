package com.test.endtecttest.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.endtecttest.data.repository.UserRepository
import com.test.endtecttest.ui.intent.ItemsIntent
import com.test.endtecttest.ui.viewstate.MainState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception


@ExperimentalCoroutinesApi
class MainViewModel(
        private val userRepository: UserRepository,
        private val context: Context
    ):ViewModel(){
    val userIntent = Channel<ItemsIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it){
                    is ItemsIntent.GetItems -> getItems()
                }
            }
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try{
                userRepository.getUsers().observe((context as LifecycleOwner), {
                    Log.d("Debug", "fetchUser: ${it.size}")
                    _state.value =  MainState.Users(it)
                })
            }
            catch (e: Exception){
                Log.d("Debug", "fetchUser: ${e.message}")
                MainState.Error(e.message)
            }
        }
    }

}