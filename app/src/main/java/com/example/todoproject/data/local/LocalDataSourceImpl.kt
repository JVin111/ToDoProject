package com.example.todoproject.data.local

import com.example.todoproject.data.local.models.CardLocalModel
import com.example.todoproject.data.local.models.StatusLocalModel

class LocalDataSourceImpl : LocalDataSource {

    private val statusData = arrayListOf(
        StatusLocalModel(1, "To do")
    )

    private val cardData = arrayListOf(
        CardLocalModel(statusData[0],"Card name", "aaa"),
        CardLocalModel(statusData[0],"Card name", "bbb"),
        CardLocalModel(statusData[0],"Card name", "ccc")
    )

    override suspend fun getStatusList(): List<StatusLocalModel> {
        return statusData
    }

    override suspend fun addStatus(statusModel: StatusLocalModel) {
        statusData.add(statusModel)
    }

    override suspend fun updateStatus(statusModel: StatusLocalModel) {
    }

    override suspend fun deleteStatus(statusModel: StatusLocalModel) {
    }

    override suspend fun getCardList(): List<CardLocalModel> {
        return cardData
    }

    override suspend fun addCard(cardModel: CardLocalModel) {
        cardData.add(cardModel)
    }

    override suspend fun updateCard(cardModel: CardLocalModel) {
    }

    override suspend fun deleteCard(cardModel: CardLocalModel) {
    }
}