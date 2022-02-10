package com.test.endtecttest.repository

import androidx.lifecycle.LiveData
import com.test.endtecttest.domain.model.Item

interface MainRepository {
    fun getUsers(): LiveData<List<Item>>

    fun onClear()
}