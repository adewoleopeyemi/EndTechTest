package com.test.endtecttest.data.api

import androidx.lifecycle.LiveData
import com.test.endtecttest.data.model.Item

interface ApiHelper {
    fun getFisrtItems()
    fun getSecondItems()
    fun getAllItems() : LiveData<List<Item>>
}