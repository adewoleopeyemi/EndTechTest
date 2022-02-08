package com.test.endtecttest.data.entity

import com.google.gson.annotations.SerializedName
import com.test.endtecttest.data.model.Item

data class Response (
    @SerializedName("products")
    val product: List<Item>
)