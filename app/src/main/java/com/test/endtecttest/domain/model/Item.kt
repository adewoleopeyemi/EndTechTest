package com.test.endtecttest.domain.model

import com.google.gson.annotations.SerializedName


data class Item(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: String = "",
    @SerializedName("image")
    val image: String = ""
)
