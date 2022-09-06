package com.example.todoproject.domain.interactors.card_editing

import com.example.todoproject.domain.State
import com.example.todoproject.domain.enitities.CardModel

data class CardEditingState(val selectedCard: CardModel? = null, val cardList: List<CardModel>? = listOf()) : State {

}
