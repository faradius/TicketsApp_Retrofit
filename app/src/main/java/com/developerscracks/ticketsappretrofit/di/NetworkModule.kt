package com.developerscracks.ticketsappretrofit.di

import com.developerscracks.ticketsappretrofit.core.Config.BASE_URL
import com.developerscracks.ticketsappretrofit.data.network.api.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun providesTicketsApiClient(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

}