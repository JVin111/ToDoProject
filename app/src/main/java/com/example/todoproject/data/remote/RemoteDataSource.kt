package com.example.todoproject.data.remote

import com.example.todoproject.data.remote.models.CardRemoteModel

interface RemoteDataSource {

    suspend fun getCardList(): List<CardRemoteModel>

    suspend fun addCard(cardModel: CardRemoteModel)

    suspend fun updateCard(cardModel: CardRemoteModel)

    suspend fun deleteCard(cardModel: CardRemoteModel)
}