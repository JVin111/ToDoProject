package com.example.todoproject.ui.board_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todoproject.base.BaseViewModel
import com.example.todoproject.domain.enitities.CardModel
import com.example.todoproject.domain.enitities.ColumnModel
import com.example.todoproject.domain.enitities.StatusModel
import com.example.todoproject.domain.interactors.card_editing.CardEditingInteractor
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardListViewModel(private val cardEditingInteractor: CardEditingInteractor) : BaseViewModel() {

    private val _screenStateFlow = MutableStateFlow<BoardState>(BoardState.BoardLoading)
    val screenStateFlow: StateFlow<BoardState> = _screenStateFlow

    init {
        viewModelScope.launch {
            cardEditingInteractor.interactorStateFlow.map { state ->
                BoardState.BoardLoaded(listOf(ColumnModel(StatusModel(1, "To do"), state.cardList!!), ColumnModel(StatusModel(1, "Doing"), state.cardList!!)))
            }.collect {
                _screenStateFlow.emit(it)
            }
        }
    }

    fun onCardClick(columnIndex: Int, cardIndex: Int) {
        if (screenStateFlow.value is BoardState.BoardLoaded) {
            cardEditingInteractor.setCard((screenStateFlow.value as BoardState.BoardLoaded).columnList[columnIndex].cardList[cardIndex])
        }
        navigateTo(BoardFragmentDirections.actionCardListFragmentToCardDataFragment())
    }

    fun onAddClick(columnIndex: Int) {
        if (screenStateFlow.value is BoardState.BoardLoaded) {
            val statusModel = (screenStateFlow.value as BoardState.BoardLoaded).columnList[columnIndex].status
            cardEditingInteractor.setCard(CardModel(statusModel, "", ""))
        }
        navigateTo(BoardFragmentDirections.actionCardListFragmentToCardDataFragment())
    }
}

class CardListViewModelFactory @Inject constructor(private val cardEditingInteractor: CardEditingInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardListViewModel(cardEditingInteractor) as T
    }
}