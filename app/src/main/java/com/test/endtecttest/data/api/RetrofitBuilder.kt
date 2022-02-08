package com.test.endtecttest.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {
    private const val BASE_URL = "https://www.endclothing.com/media/catalog/"

    val apiService: ApiService = getRetrofit()

    private fun getRetrofit() : ApiService {
        val httpClient = provideLoggingCapableHttpClient()
        val gson = provideGsonConverter()
        val rxJavaCallAdapterFactory = provideRxJavaCallAdapterFactory()
        val retrofit = provideRetrofitBuilder(httpClient, gson, rxJavaCallAdapterFactory)
        return provideRestService(retrofit, BASE_URL)
    }

    private fun provideLoggingCapableHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        gson: Gson,
        rxJavaCallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit.Builder {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .client(okHttpClient)
    }

    private fun provideRestService(retrofitBuilder: Retrofit.Builder, baseUrl: String): ApiService {
        return retrofitBuilder.baseUrl(baseUrl)
            .build()
            .create(ApiService::class.java)
    }

    private fun provideGsonConverter(): Gson {
        return GsonBuilder().create()
    }

    private fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
}