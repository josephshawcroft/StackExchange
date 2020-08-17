package com.josephshawcroft.stackexchangeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    private val BASE_URL = "https://api.stackexchange.com/2.2"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val retroBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
        retroBuilder.client(OkHttpClient())

        return retroBuilder.build()
    }
}