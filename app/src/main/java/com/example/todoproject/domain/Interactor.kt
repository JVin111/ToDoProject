package com.example.todoproject.domain

import kotlinx.coroutines.flow.StateFlow

abstract class Interactor<ACTION : Action, STATE : State>(protected val store: Store, val updateState: (newState: STATE) -> Unit) {

    abstract val interactorStateFlow: StateFlow<STATE>

    abstract fun handleAction(action: ACTION)
}