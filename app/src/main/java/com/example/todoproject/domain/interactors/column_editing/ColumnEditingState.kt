package com.example.todoproject.domain.interactors.column_editing

import com.example.todoproject.domain.State
import com.example.todoproject.domain.enitities.ColumnModel

data class ColumnEditingState(val columnList: List<ColumnModel> = listOf()) : State
