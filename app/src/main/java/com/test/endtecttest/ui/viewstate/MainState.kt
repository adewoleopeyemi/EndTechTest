package com.test.endtecttest.ui.viewstate

import com.test.endtecttest.data.model.Item

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class Users(val items: List<Item>): MainState()
    data class Error(val error: String?): MainState()
}