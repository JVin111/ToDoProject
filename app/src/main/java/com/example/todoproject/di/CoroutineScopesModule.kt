package com.example.todoproject.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class CoroutineScopesModule {

    @Singleton
    @Provides
    @ApplicationScope
    fun provideCoroutineApplicationScope(@DefaultDispatcher defaultDispatcher: CoroutineDispatcher): CoroutineScope {
        return CoroutineScope(defaultDispatcher + SupervisorJob())
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope