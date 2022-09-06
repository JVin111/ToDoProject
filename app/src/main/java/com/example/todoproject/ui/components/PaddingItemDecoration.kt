package com.example.todoproject.ui.components

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PaddingItemDecoration(private val padding: Padding) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            padding.left?.let {
                left = it
            }
            padding.top?.let {
                top = it
            }
            padding.right?.let {
                right = it
            }
            padding.bottom?.let {
                bottom = it
            }
        }
    }
}