package com.test.endtecttest.network.entity

import com.google.gson.annotations.SerializedName
import com.test.endtecttest.domain.model.Item

data class Response (
    @SerializedName("products")
    val product: List<Item>
)