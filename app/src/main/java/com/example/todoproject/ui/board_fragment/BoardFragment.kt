package com.example.todoproject.ui.board_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.R
import com.example.todoproject.applicationComponent
import com.example.todoproject.base.BaseFragment
import com.example.todoproject.ui.components.Padding
import com.example.todoproject.ui.components.PaddingItemDecoration
import com.example.todoproject.databinding.BoardFragmentBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class BoardFragment : BaseFragment<BoardFragmentBinding, CardListViewModel>() {

    override var _binding: BoardFragmentBinding? = null
    private lateinit var cardsRecyclerView: RecyclerView

    @Inject
    lateinit var cardListViewModelFactory: CardListViewModelFactory
    override lateinit var viewModel: CardListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BoardFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        initBoardViewModel()
        initColumnsRecyclerView()
        return view
    }

    private fun initBoardViewModel() {
        this.viewModel = ViewModelProvider(this, cardListViewModelFactory).get(CardListViewModel::class.java)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenStateFlow.collect { state ->
                    setScreenState(state)
                }
            }
        }
    }

    private fun initColumnsRecyclerView() {
        cardsRecyclerView = binding.columnsRecyclerView
        cardsRecyclerView.addItemDecoration(PaddingItemDecoration(Padding(all = resources.getDimensionPixelSize(R.dimen.columns_padding))))
        cardsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayout.HORIZONTAL, false)
        cardsRecyclerView.adapter = ColumnsAdapter(object : ColumnsAdapter.OnColumnClickListener {
            override fun onItemInColumnClick(columnPosition: Int, cardPosition: Int) {
                viewModel.onCardClick(columnPosition, cardPosition)
            }

            override fun onAddCardClick(columnPosition: Int) {
                viewModel.onAddClick(columnPosition)
            }
        }, Padding(all = resources.getDimensionPixelSize(R.dimen.card_items_padding)))
    }

    private fun setScreenState(state: BoardState) {
        when (state) {
            is BoardState.BoardLoading -> {
                (cardsRecyclerView.adapter as ColumnsAdapter).setCardList(listOf())
            }
            is BoardState.BoardLoaded -> {
                (cardsRecyclerView.adapter as ColumnsAdapter).setCardList(state.columnList)
                cardsRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
}