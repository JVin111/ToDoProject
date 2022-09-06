package com.example.todoproject.di

import com.example.todoproject.ToDoProjectApplication
import com.example.todoproject.domain.StoreImpl
import com.example.todoproject.ui.card_data_fragment.CardDataFragment
import com.example.todoproject.ui.board_fragment.BoardFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CoroutineDispatchersModule::class,
    CoroutineScopesModule::class,
    DataModule::class,
    DomainModule::class
])

interface AppComponent {

    fun inject(toDoProjectApplication: ToDoProjectApplication)

    fun inject(boardFragment: BoardFragment)

    fun inject(cardDataFragment: CardDataFragment)

    fun inject(store: StoreImpl)
}