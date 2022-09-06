package com.example.todoproject.domain.interactors.card_editing

import com.example.todoproject.domain.enitities.CardModel
import com.example.todoproject.domain.enitities.StatusModel

interface CardEditingInput {

    fun setCard(card: CardModel?)

    fun createCard(status: StatusModel, name: String, text: String)

    fun editCard()

    fun deleteCard()
}