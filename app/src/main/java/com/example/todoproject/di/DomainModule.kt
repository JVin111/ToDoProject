package com.example.todoproject.di

import com.example.todoproject.data.CardRepository
import com.example.todoproject.domain.StoreImpl
import com.example.todoproject.domain.interactors.card_editing.CardEditingInteractor
import com.example.todoproject.domain.interactors.column_editing.ColumnEditingInteractor
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideStore(@ApplicationScope applicationScope: CoroutineScope): StoreImpl {
        return StoreImpl(applicationScope = applicationScope)
    }

    @Singleton
    @Provides
    fun provideCardEditingInteractor(cardRepository: CardRepository, @ApplicationScope applicationScope: CoroutineScope, store: StoreImpl): CardEditingInteractor {
        return CardEditingInteractor(cardRepository = cardRepository, applicationScope = applicationScope, store = store, updateState = store::updateState)
    }

    @Singleton
    @Provides
    fun provideColumnEditingInteractor(cardRepository: CardRepository, @ApplicationScope applicationScope: CoroutineScope, store: StoreImpl): ColumnEditingInteractor {
        return ColumnEditingInteractor(cardRepository = cardRepository, applicationScope = applicationScope, store = store, updateState = store::updateState)
    }
}