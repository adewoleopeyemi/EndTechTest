package com.test.endtecttest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.endtecttest.domain.model.Item
import com.test.endtecttest.network.api.ApiHelper
import com.test.endtecttest.network.api.ApiHelperImpl
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainRepositoryImplTest: TestCase(){
    private val repository: MainRepositoryImpl
    private val apiHelper: ApiHelper
    init {
        apiHelper = mock(ApiHelperImpl::class.java)
        repository = MainRepositoryImpl(apiHelper)
    }

    @Test
    fun testGetUser(){
        val result: LiveData<List<Item>> = MutableLiveData()
        Mockito.`when`(repository.getUsers()).thenReturn(result)
        assertEquals(result.value, apiHelper.getAllItems().value)
    }
}