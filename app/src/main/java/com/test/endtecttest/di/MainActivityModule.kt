package com.test.endtecttest.di

import com.test.endtecttest.network.api.ApiHelper
import com.test.endtecttest.repository.MainRepository
import com.test.endtecttest.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object MainActivityModule {
    @Provides
    fun provideMainRepository(
        apiHelper: ApiHelper
    ): MainRepository{
        return MainRepositoryImpl(apiHelper)
    }
}