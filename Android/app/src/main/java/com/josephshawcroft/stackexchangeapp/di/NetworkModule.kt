package com.josephshawcroft.stackexchangeapp.di

import com.josephshawcroft.stackexchangeapp.network.ApiClient
import com.josephshawcroft.stackexchangeapp.network.StackExchangeService
import com.josephshawcroft.stackexchangeapp.userlist.IUserListRepository
import com.josephshawcroft.stackexchangeapp.userlist.UserListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    private val BASE_URL = "https://api.stackexchange.com/2.2/"

    @Provides
    @Singleton
    fun provideStackExchangeService(): StackExchangeService {

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        val retroBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)

        val retrofit = retroBuilder.build()
        return retrofit.create(StackExchangeService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiClient(stackExchangeService: StackExchangeService): ApiClient =
        ApiClient(stackExchangeService)

    @Provides
    @Singleton
    fun provideUserRepository(apiClient: ApiClient): IUserListRepository =
        UserListRepository(apiClient)
}