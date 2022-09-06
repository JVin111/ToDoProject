package com.example.todoproject.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<B : ViewBinding, VM : BaseViewModel> : Fragment() {

    protected abstract var _binding: B?
    protected val binding
        get() = _binding!!
    abstract var viewModel: VM
    private lateinit var navigationJob: Job

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigationActions()
    }

    private fun observeNavigationActions() {
        navigationJob = lifecycleScope.launch {
            viewModel.navigation.collect { navigationAction ->
                navigationAction?.let {
                    handleNavigationAction(navigationAction)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        navigationJob.cancel()
    }

    private fun handleNavigationAction(action: NavigationAction) {
        when (action) {
            is NavigationAction.NavigateTo -> Navigation.findNavController(binding.root).navigate(action.directions)
            is NavigationAction.NavigateBack -> Navigation.findNavController(binding.root).popBackStack()
        }
    }
}