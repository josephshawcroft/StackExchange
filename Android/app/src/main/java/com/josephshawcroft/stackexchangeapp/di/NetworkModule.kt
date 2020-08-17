package com.josephshawcroft.stackexchangeapp.di

import com.josephshawcroft.stackexchangeapp.network.ApiClient
import com.josephshawcroft.stackexchangeapp.userlist.IUserListRepository
import com.josephshawcroft.stackexchangeapp.userlist.UserListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.josephshawcroft.stackexchangeapp.BuildConfig

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiClient = ApiClient(retrofit)

    @Provides
    @Singleton
    fun provideUserRepository(apiClient: ApiClient): IUserListRepository =
        UserListRepository(apiClient)
}