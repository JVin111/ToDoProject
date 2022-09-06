package com.example.todoproject.data.local.models

import com.example.todoproject.domain.enitities.CardModel
import com.example.todoproject.domain.enitities.StatusModel

data class CardLocalModel(val status: StatusLocalModel, val name: String, val text: String) {

    constructor(card: CardModel) : this(
        status = StatusLocalModel(card.status),
        name = card.name,
        text = card.text
    )

    fun toCardModel(): CardModel {
        return CardModel(
            status = status.toStatusModel(),
            name = name,
            text = text
        )
    }
}