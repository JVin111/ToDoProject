package com.example.todoproject.di

import com.example.todoproject.data.CardRepository
import com.example.todoproject.data.RepositoryImpl
import com.example.todoproject.data.local.LocalDataSource
import com.example.todoproject.data.local.LocalDataSourceImpl
import com.example.todoproject.data.remote.RemoteDataSource
import com.example.todoproject.data.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideCardRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): CardRepository {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSourceImpl()
    }

    @Provides
    fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }
}