package com.example.todoproject.data

import com.example.todoproject.data.local.LocalDataSource
import com.example.todoproject.data.local.models.CardLocalModel
import com.example.todoproject.data.remote.RemoteDataSource
import com.example.todoproject.domain.enitities.CardModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RepositoryImpl(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) : CardRepository {

    override suspend fun getCards(): Flow<List<CardModel>> {
        return flow {
            emit(localDataSource.getCardList().map {
                it.toCardModel()
            })
        }
    }

    override suspend fun addCard(cardModel: CardModel) {
        localDataSource.addCard(CardLocalModel(cardModel))
    }

    override suspend fun updateCard(cardModel: CardModel) {
    }

    override suspend fun deleteCard(cardModel: CardModel) {
    }
}