package com.example.todoproject.data

import com.example.todoproject.domain.enitities.CardModel
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun getCards(): Flow<List<CardModel>>

    suspend fun addCard(cardModel: CardModel)

    suspend fun updateCard(cardModel: CardModel)

    suspend fun deleteCard(cardModel: CardModel)
}