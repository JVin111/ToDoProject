package com.example.todoproject.ui.board_fragment

import com.example.todoproject.domain.enitities.ColumnModel

sealed class BoardState {
    object BoardLoading : BoardState()
    data class BoardLoaded(val columnList: List<ColumnModel>) : BoardState()
}