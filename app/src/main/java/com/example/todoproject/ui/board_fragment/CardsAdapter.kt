package com.example.todoproject.ui.board_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.databinding.CardListItemBinding
import com.example.todoproject.domain.enitities.CardModel

class CardsAdapter : RecyclerView.Adapter<CardsAdapter.ViewHolder>() {

    private var cardList: List<CardModel> = listOf()
    private var onCardClickListener: OnCardClickListener? = null

    fun setCardList(newList: List<CardModel>) {
        cardList = newList
    }

    fun setOnCardClickListener(onCardClickListener: OnCardClickListener) {
        this.onCardClickListener = onCardClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.cardTitleTextView.text = cardList[position].name
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    interface OnCardClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(val binding: CardListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onCardClickListener?.onItemClick(adapterPosition)
            }
        }
    }
}