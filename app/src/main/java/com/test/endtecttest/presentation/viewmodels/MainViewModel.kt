package com.test.endtecttest.presentation.viewmodels

import androidx.lifecycle.*
import com.test.endtecttest.domain.model.Item
import com.test.endtecttest.presentation.intent.ItemsIntent
import com.test.endtecttest.presentation.viewstate.MainViewState
import com.test.endtecttest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
    @Inject constructor(
        private val mainRepository: MainRepository
    ):ViewModel(){
    val userIntent = Channel<ItemsIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val viewState: StateFlow<MainViewState> = _state
    private lateinit var _itemsLiveData: LiveData<List<Item>>
    private val _itemsObserver = Observer<List<Item>> {
        _state.value = MainViewState.Items(it)
    }

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
            _state.value = MainViewState.Loading
            try{
                _itemsLiveData =  mainRepository.getUsers()
                _itemsLiveData.observeForever(_itemsObserver)
            }
            catch (e: Exception){
                MainViewState.Error(e.message)
            }
        }
    }

    override fun onCleared() {
        mainRepository.onClear()
        _itemsLiveData.removeObserver(_itemsObserver)
        super.onCleared()
    }
}