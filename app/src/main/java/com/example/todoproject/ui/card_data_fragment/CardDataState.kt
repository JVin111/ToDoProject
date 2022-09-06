package com.example.todoproject.ui.card_data_fragment

import com.example.todoproject.domain.enitities.CardModel


sealed class CardDataState {
    object CardDataLoading : CardDataState()
    data class CardDataLoaded(val cardModel: CardModel) : CardDataState()
}
