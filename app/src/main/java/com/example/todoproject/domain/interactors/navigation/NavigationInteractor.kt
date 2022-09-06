package com.example.todoproject.domain.interactors.navigation

import com.example.todoproject.domain.Interactor
import com.example.todoproject.domain.Store
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Singleton

@Singleton
class NavigationInteractor(
    private val applicationScope: CoroutineScope,
    store: Store,
    updateState: (NavigationState) -> Unit
) : NavigationInput, Interactor<NavigationAction, NavigationState>(store, updateState) {

    override val interactorStateFlow: StateFlow<NavigationState> = store.appState.navigationStateFlow
    private var state: NavigationState = interactorStateFlow.value

    init {
        applicationScope.launch {
            interactorStateFlow.collect {
                state = it
            }
        }
    }

    override fun navigateTo() {
    }

    override fun navigateBack() {
    }

    override fun handleAction(action: NavigationAction) {
    }
}