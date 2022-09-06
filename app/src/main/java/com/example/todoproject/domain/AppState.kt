package com.example.todoproject.domain

import com.example.todoproject.domain.interactors.card_editing.CardEditingState
import com.example.todoproject.domain.interactors.column_editing.ColumnEditingState
import com.example.todoproject.domain.interactors.navigation.NavigationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppStateImpl : AppState {

    private val _navigationStateFlow = MutableStateFlow(NavigationState())
    private val _cardEditingStateFlow = MutableStateFlow(CardEditingState())
    private val _columnEditingStateFlow = MutableStateFlow(ColumnEditingState())

    override val navigationStateFlow = _navigationStateFlow
    override val cardEditingStateFlow = _cardEditingStateFlow
    override val columnEditingStateFlow = _columnEditingStateFlow

    fun <STATE : State> updateState(state: STATE) {
        when(state) {
            is CardEditingState -> _cardEditingStateFlow.value = state
            is ColumnEditingState -> _columnEditingStateFlow.value = state
        }
    }
}

interface AppState {
    val navigationStateFlow: StateFlow<NavigationState>
    val cardEditingStateFlow: StateFlow<CardEditingState>
    val columnEditingStateFlow: StateFlow<ColumnEditingState>
}