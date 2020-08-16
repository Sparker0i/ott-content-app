package me.sparker0i.ottcontent.view.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CardAdapter<T>(bl: Int): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    abstract var items: List<T>
    val baseLayout = bl

    internal abstract inner class ItemViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}