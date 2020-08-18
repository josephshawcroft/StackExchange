package com.josephshawcroft.stackexchangeapp.di

import com.josephshawcroft.stackexchangeapp.network.ApiClient
import com.josephshawcroft.stackexchangeapp.repository.IUserRepository
import com.josephshawcroft.stackexchangeapp.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiClient: ApiClient): IUserRepository =
        UserRepository(apiClient)
}