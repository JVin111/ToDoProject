package com.example.todoproject.base

import androidx.navigation.NavDirections

sealed class NavigationAction {
    data class NavigateTo(val directions: NavDirections) : NavigationAction()
    object NavigateBack : NavigationAction()
}
