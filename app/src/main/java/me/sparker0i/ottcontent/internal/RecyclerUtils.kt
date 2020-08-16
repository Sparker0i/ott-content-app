package me.sparker0i.ottcontent.internal

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.view.base.CardAdapter

fun<T> setupGridRecyclerView(cardAdapter: CardAdapter<T>, recyclerView: RecyclerView, list: List<T>) {
    recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
    recyclerView.itemAnimator = DefaultItemAnimator()
    recyclerView.clipToPadding = false
    recyclerView.clipChildren = false

    val spacing = recyclerView.context.resources.getDimensionPixelSize(R.dimen.card_spacing)
    recyclerView.setPadding(spacing, spacing, spacing, spacing)

    recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.set(spacing, spacing, spacing, spacing)
        }
    })

    cardAdapter.items = list
    recyclerView.adapter = cardAdapter
}