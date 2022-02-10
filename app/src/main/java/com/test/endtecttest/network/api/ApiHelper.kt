package com.test.endtecttest.network.api

import androidx.lifecycle.LiveData
import com.test.endtecttest.domain.model.Item

interface ApiHelper {
    fun getFisrtItems()
    fun getSecondItems()
    fun getAllItems() : LiveData<List<Item>>
    fun onClear()
}