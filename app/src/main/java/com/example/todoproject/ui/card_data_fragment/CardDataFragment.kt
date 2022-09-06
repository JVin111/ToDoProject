package com.example.todoproject.ui.card_data_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.todoproject.applicationComponent
import com.example.todoproject.base.BaseFragment
import com.example.todoproject.databinding.CardDataFragmentBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardDataFragment : BaseFragment<CardDataFragmentBinding, CardViewModel>() {

    override var _binding: CardDataFragmentBinding? = null
    private lateinit var cardStatusButton: Button
    private lateinit var cardNameEditText: TextInputEditText
    private lateinit var cardTextEditText: TextInputEditText
    private lateinit var saveButton: Button

    @Inject
    lateinit var cardViewModelFactory: CardViewModelFactory
    override lateinit var viewModel: CardViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = CardDataFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        initCardViewModel()
        initCardStatusTextView()
        initCardNameEditText()
        initCardTextEditText()
        initSaveButton()
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.cancelChanges()
        }
        return view
    }

    private fun initCardViewModel() {
        viewModel = ViewModelProvider(this, cardViewModelFactory).get(CardViewModel::class.java)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenStateFlow.collect { state ->
                    setScreenState(state)
                }
            }
        }
    }

    private fun initCardStatusTextView() {
        cardStatusButton = binding.cardStatusButton
    }

    private fun initCardNameEditText() {
        cardNameEditText = binding.cardNameEditText
    }

    private fun initCardTextEditText() {
        cardTextEditText = binding.cardTextEditText
    }

    private fun initSaveButton() {
        saveButton = binding.saveButton
        saveButton.setOnClickListener {
            viewModel.saveCard(cardNameEditText.text.toString(), cardTextEditText.text.toString())
        }
    }

    private fun setScreenState(state: CardDataState) {
        when (state) {
            is CardDataState.CardDataLoading -> {
                cardStatusButton.text = ""
                cardNameEditText.setText("")
                cardTextEditText.setText("")
            }
            is CardDataState.CardDataLoaded -> {
                cardStatusButton.text = state.cardModel.status.name
                cardNameEditText.setText(state.cardModel.name)
                cardTextEditText.setText(state.cardModel.text)
            }
        }
    }
}