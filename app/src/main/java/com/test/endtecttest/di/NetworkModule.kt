package com.test.endtecttest.di

import com.test.endtecttest.network.api.ApiHelper
import com.test.endtecttest.network.api.ApiHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesNetworkModule(): ApiHelper {
        return ApiHelperImpl()
    }
}