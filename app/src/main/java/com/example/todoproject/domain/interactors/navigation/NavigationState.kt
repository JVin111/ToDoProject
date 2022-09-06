package com.example.todoproject.domain.interactors.navigation

import com.example.todoproject.domain.State
import com.example.todoproject.domain.enitities.CardModel

data class NavigationState(val selectedCard: CardModel? = null, val cardList: List<CardModel>? = listOf()) : State {

}
