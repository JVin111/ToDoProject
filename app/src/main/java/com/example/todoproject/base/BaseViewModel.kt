package com.example.todoproject.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableSharedFlow<NavigationAction>(replay = 0)
    val navigation: SharedFlow<NavigationAction?> get() = _navigation

    fun navigateTo(navDirections: NavDirections) {
        viewModelScope.launch {
            _navigation.emit(NavigationAction.NavigateTo(navDirections))
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _navigation.emit(NavigationAction.NavigateBack)
        }
    }
}