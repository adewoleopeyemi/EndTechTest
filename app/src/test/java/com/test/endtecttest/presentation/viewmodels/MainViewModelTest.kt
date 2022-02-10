package com.test.endtecttest.presentation.viewmodels

import com.test.endtecttest.presentation.intent.ItemsIntent
import com.test.endtecttest.presentation.viewstate.MainViewState
import com.test.endtecttest.repository.MainRepository
import com.test.endtecttest.repository.MainRepositoryImpl
import junit.framework.TestCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest : TestCase(){
    private val mainViewModel: MainViewModel
    private val mainRepository: MainRepository
    val scope = GlobalScope
    init {
        mainRepository = mock(MainRepositoryImpl::class.java)
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun getItems() = runBlocking(){
        val result = MutableStateFlow<MainViewState>((MainViewState.Idle))
        scope.launch {
            mainViewModel.userIntent.send(ItemsIntent.GetItems)
            mainViewModel.viewState.collect{
                Mockito.`when`(it).thenReturn(result.value)
            }
        }
        assertEquals(result.value, mainRepository.getUsers())
    }
}