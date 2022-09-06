package com.example.todoproject.data.local.models

import com.example.todoproject.domain.enitities.StatusModel

data class StatusLocalModel(val id: Int, var name: String) {

    constructor(status: StatusModel) : this(
        id = status.id,
        name = status.name,
    )

    fun toStatusModel(): StatusModel {
        return StatusModel(
            id = id,
            name = name,
        )
    }
}