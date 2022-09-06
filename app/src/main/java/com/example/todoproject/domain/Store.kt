package com.example.todoproject.domain

import com.example.todoproject.domain.interactors.card_editing.CardEditingAction
import com.example.todoproject.domain.interactors.card_editing.CardEditingInteractor
import com.example.todoproject.domain.interactors.column_editing.ColumnEditingAction
import com.example.todoproject.domain.interactors.column_editing.ColumnEditingInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreImpl(applicationScope: CoroutineScope) : Store, StateUpdater {

    private var _appState: AppStateImpl = AppStateImpl()
    override val appState: AppState = _appState
    private val actionFlow = MutableSharedFlow<Action>()

    @Inject
    lateinit var cardEditingInteractor: CardEditingInteractor
    @Inject
    lateinit var columnEditingInteractor: ColumnEditingInteractor

    init {
        applicationScope.launch {
            actionFlow.collect { action ->
                when (action) {
                    is CardEditingAction -> cardEditingInteractor.handleAction(action)
                    is ColumnEditingAction -> columnEditingInteractor.handleAction(action)
                }
            }
        }
    }

    override suspend fun postAction(action: Action) {
        actionFlow.emit(action)
    }

    override fun <STATE : State> updateState(newState: STATE) {
        _appState.updateState(newState)
    }
}

interface Store {
    val appState: AppState

    suspend fun postAction(action: Action)
}

interface StateUpdater {
    fun <STATE : State> updateState(newState: STATE)
}