package me.sparker0i.ottcontent.view.composer.countrypicker.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemDetailsLookup.ItemDetails
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.viewmodel.ContentViewModel

internal class CountryAdapter(vm: ContentViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var items: List<Country>
    lateinit var selectionTracker: SelectionTracker<Long?>
    private val viewModel = vm

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_country_new, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        (viewHolder as ItemViewHolder).bind(item, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val details: Details = Details()
        private val materialCardView: MaterialCardView = itemView.findViewById(R.id.item_country_card)
        private val countryText: TextView = itemView.findViewById(R.id.tv_country_name)
        private val countryImage: ImageView = itemView.findViewById(R.id.iv_country_flag)

        fun bind(country: Country, position: Int) {
            details.position = position.toLong()
            countryText.text = country.name
            Glide.with(itemView)
                .load(country.flag)
                .centerInside()
                .into(countryImage)
            materialCardView.setOnClickListener {
                viewModel.countryValue.postValue(country.code)
            }
            bindSelectedState()
        }

        private fun bindSelectedState() {
            materialCardView.isChecked = selectionTracker.isSelected(details.selectionKey)
        }

        val itemDetails: ItemDetails<Long?>
            get() = details
    }

    internal class DetailsLookup(private val recyclerView: RecyclerView): ItemDetailsLookup<Long?>() {
        override fun getItemDetails(e: MotionEvent): ItemDetails<Long?>? {
            val view = recyclerView.findChildViewUnder(e.x, e.y)
            if (view != null) {
                val viewHolder = recyclerView.getChildViewHolder(view)
                if (viewHolder is ItemViewHolder) {
                    return viewHolder.itemDetails
                }
            }
            return null
        }

    }

    internal class KeyProvider(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) :
        ItemKeyProvider<Long?>(SCOPE_MAPPED) {
        override fun getKey(position: Int): Long? {
            return position.toLong()
        }

        override fun getPosition(key: Long): Int {
            return key.toInt()
        }
    }

    internal class Details : ItemDetailsLookup.ItemDetails<Long?>() {
        var position: Long = 0
        override fun getPosition(): Int {
            return position.toInt()
        }

        override fun getSelectionKey(): Long {
            return position
        }

        override fun inSelectionHotspot(e: MotionEvent): Boolean {
            return false
        }

        override fun inDragRegion(e: MotionEvent): Boolean {
            return true
        }
    }

}