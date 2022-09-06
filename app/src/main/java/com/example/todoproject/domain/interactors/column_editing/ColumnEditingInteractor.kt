package com.example.todoproject.domain.interactors.column_editing

import com.example.todoproject.data.CardRepository
import com.example.todoproject.domain.Interactor
import com.example.todoproject.domain.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Singleton
class ColumnEditingInteractor(
    private val cardRepository: CardRepository,
    private val applicationScope: CoroutineScope,
    store: Store,
    updateState: (ColumnEditingState) -> Unit
) : ColumnEditingInput, Interactor<ColumnEditingAction, ColumnEditingState>(store, updateState) {

    override val interactorStateFlow: StateFlow<ColumnEditingState> = store.appState.columnEditingStateFlow
    val state = interactorStateFlow.value

    override fun createColumn() {
    }

    override fun deleteColumn() {
    }

    override fun handleAction(action: ColumnEditingAction) {
    }
}