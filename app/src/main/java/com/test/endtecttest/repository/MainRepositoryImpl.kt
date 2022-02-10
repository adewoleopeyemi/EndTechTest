package com.test.endtecttest.repository

import androidx.lifecycle.LiveData
import com.test.endtecttest.network.api.ApiHelper
import com.test.endtecttest.domain.model.Item
import javax.inject.Inject

class MainRepositoryImpl
    @Inject constructor(private val apiHelper: ApiHelper) :MainRepository{
    override fun getUsers(): LiveData<List<Item>>{
        return apiHelper.getAllItems()
    }

    override fun onClear() {
        return apiHelper.onClear()
    }
}