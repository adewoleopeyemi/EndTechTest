package com.test.endtecttest.data.api

import com.test.endtecttest.data.entity.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("android_test_example.json")
    fun getFirstItems(): Observable<Response>

    @GET("example.json")
    fun getSecondItems(): Observable<Response>
}