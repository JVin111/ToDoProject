package com.example.todoproject.ui.card_data_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todoproject.base.BaseViewModel
import com.example.todoproject.domain.interactors.card_editing.CardEditingInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardViewModel(private val cardEditingInteractor: CardEditingInteractor) : BaseViewModel() {

    private val _screenStateFlow = MutableStateFlow<CardDataState>(CardDataState.CardDataLoading)
    val screenStateFlow: StateFlow<CardDataState> = _screenStateFlow

    init {
        viewModelScope.launch {
            cardEditingInteractor.interactorStateFlow.map { state ->
                if (state.selectedCard != null) {
                    CardDataState.CardDataLoaded(state.selectedCard)
                } else {
                    CardDataState.CardDataLoading
                }
            }.collect {
                _screenStateFlow.emit(it)
            }
        }
    }

    fun saveCard(name: String, text: String) {
        if (screenStateFlow.value is CardDataState.CardDataLoaded) {
            val currentCard = (screenStateFlow.value as CardDataState.CardDataLoaded).cardModel
            cardEditingInteractor.createCard(currentCard.status, name, text)
            navigateBack()
        }
    }

    fun cancelChanges() {
        cardEditingInteractor.setCard(null)
        navigateBack()
    }
}

class CardViewModelFactory @Inject constructor(private val cardEditingInteractor: CardEditingInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardViewModel(cardEditingInteractor) as T
    }
}