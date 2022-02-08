package com.test.endtecttest.data.repository

import androidx.lifecycle.LiveData
import com.test.endtecttest.data.api.ApiHelper
import com.test.endtecttest.data.model.Item

class UserRepository(private val apiHelper: ApiHelper) {
    fun getUsers(): LiveData<List<Item>>{
        return apiHelper.getAllItems()
    }
}