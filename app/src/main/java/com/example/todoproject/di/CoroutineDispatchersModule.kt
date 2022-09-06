package com.example.todoproject.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class CoroutineDispatchersModule {

    @Singleton
    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Singleton
    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    @UnconfinedDispatcher
    fun provideUnconfinedDispatcher(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }

    @Singleton
    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IODispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UnconfinedDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher