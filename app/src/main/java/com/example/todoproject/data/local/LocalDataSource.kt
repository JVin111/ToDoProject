package com.example.todoproject.data.local

import com.example.todoproject.data.local.models.CardLocalModel
import com.example.todoproject.data.local.models.StatusLocalModel

interface LocalDataSource {

    suspend fun getStatusList(): List<StatusLocalModel>

    suspend fun addStatus(statusModel: StatusLocalModel)

    suspend fun updateStatus(statusModel: StatusLocalModel)

    suspend fun deleteStatus(statusModel: StatusLocalModel)

    suspend fun getCardList(): List<CardLocalModel>

    suspend fun addCard(cardModel: CardLocalModel)

    suspend fun updateCard(cardModel: CardLocalModel)

    suspend fun deleteCard(cardModel: CardLocalModel)
}