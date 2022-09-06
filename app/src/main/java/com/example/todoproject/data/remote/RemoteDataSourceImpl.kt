package com.example.todoproject.data.remote

import com.example.todoproject.data.remote.models.CardRemoteModel

class RemoteDataSourceImpl : RemoteDataSource {

    override suspend fun getCardList(): List<CardRemoteModel> {
        return listOf()
    }

    override suspend fun addCard(cardModel: CardRemoteModel) {
    }

    override suspend fun updateCard(cardModel: CardRemoteModel) {
    }

    override suspend fun deleteCard(cardModel: CardRemoteModel) {
    }
}