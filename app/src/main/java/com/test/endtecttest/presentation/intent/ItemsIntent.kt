package com.test.endtecttest.presentation.intent

sealed class ItemsIntent {
    object GetItems: ItemsIntent()
}