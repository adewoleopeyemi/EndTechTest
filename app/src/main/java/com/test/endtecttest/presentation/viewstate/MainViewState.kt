package com.test.endtecttest.presentation.viewstate

import com.test.endtecttest.domain.model.Item

sealed class MainViewState {
    object Idle: MainViewState()
    object Loading: MainViewState()
    data class Items(val items: List<Item>): MainViewState()
    data class Error(val error: String?): MainViewState()
}