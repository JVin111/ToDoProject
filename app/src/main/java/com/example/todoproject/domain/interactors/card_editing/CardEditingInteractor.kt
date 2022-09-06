package com.example.todoproject.domain.interactors.card_editing

import com.example.todoproject.data.CardRepository
import com.example.todoproject.domain.Interactor
import com.example.todoproject.domain.Store
import com.example.todoproject.domain.enitities.CardModel
import com.example.todoproject.domain.enitities.StatusModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Singleton

@Singleton
class CardEditingInteractor(
    private val cardRepository: CardRepository,
    private val applicationScope: CoroutineScope,
    store: Store,
    updateState: (CardEditingState) -> Unit
) : CardEditingInput, Interactor<CardEditingAction, CardEditingState>(store, updateState) {

    override val interactorStateFlow: StateFlow<CardEditingState> = store.appState.cardEditingStateFlow
    private var state: CardEditingState = interactorStateFlow.value

    init {
        applicationScope.launch {
            cardRepository.getCards().collect {
                updateState(state.copy(cardList = it))
            }
            interactorStateFlow.collect {
                state = it
            }
        }
    }

    override fun setCard(card: CardModel?) {
        updateState(state.copy(selectedCard = card))
    }

    override fun createCard(status: StatusModel, name: String, text: String) {
        applicationScope.launch {
            cardRepository.addCard(CardModel(status, name, text))
            cardRepository.getCards().collect {
                updateState(state.copy(selectedCard = null, cardList = it))
            }
        }
    }

    override fun editCard() {
    }

    override fun deleteCard() {
    }

    override fun handleAction(action: CardEditingAction) {
    }
}