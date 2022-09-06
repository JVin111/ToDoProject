package com.example.todoproject.ui.board_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.ui.components.Padding
import com.example.todoproject.ui.components.PaddingItemDecoration
import com.example.todoproject.databinding.CardsColumnBinding
import com.example.todoproject.domain.enitities.ColumnModel

class ColumnsAdapter(private val onColumnClickListener: OnColumnClickListener, private val padding: Padding) : RecyclerView.Adapter<ColumnsAdapter.ViewHolder>() {

    private var columnList: List<ColumnModel> = listOf()

    fun setCardList(newList: List<ColumnModel>) {
        columnList = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardsColumnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        initCardsRecyclerView(viewHolder, binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.columnTitleTextView.text = columnList[position].status.name
        (holder.binding.cardsRecyclerView.adapter as CardsAdapter).setCardList(columnList[position].cardList)
    }

    override fun getItemCount(): Int {
        return columnList.size
    }

    private fun initCardsRecyclerView(viewHolder: ViewHolder, binding: CardsColumnBinding) {
        val cardsAdapter = CardsAdapter()
        binding.cardsRecyclerView.addItemDecoration(PaddingItemDecoration(padding))
        binding.cardsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.cardsRecyclerView.adapter = cardsAdapter
        cardsAdapter.setOnCardClickListener(object : CardsAdapter.OnCardClickListener {
            override fun onItemClick(position: Int) {
                onColumnClickListener.onItemInColumnClick(viewHolder.adapterPosition, position)
            }
        })
    }

    interface OnColumnClickListener {
        fun onItemInColumnClick(columnPosition: Int, cardPosition: Int)

        fun onAddCardClick(columnPosition: Int)
    }

    inner class ViewHolder(val binding: CardsColumnBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.addCardButton.setOnClickListener {
                onColumnClickListener.onAddCardClick(adapterPosition)
            }
        }
    }
}