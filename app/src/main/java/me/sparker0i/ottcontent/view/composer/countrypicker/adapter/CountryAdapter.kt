package me.sparker0i.ottcontent.view.composer.countrypicker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.view.base.CardAdapter
import me.sparker0i.ottcontent.viewmodel.ContentViewModel

internal class CountryAdapter(vm: ContentViewModel) : CardAdapter<Country>(R.layout.item_country) {
    override lateinit var items: List<Country>
    private val viewModel = vm

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(baseLayout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        (viewHolder as ViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal inner class ViewHolder(itemView: View): ItemViewHolder<Country>(itemView) {
        private val materialCardView: MaterialCardView = itemView.findViewById(R.id.item_country_card)
        private val countryText: TextView = itemView.findViewById(R.id.tv_country_name)
        private val countryImage: ImageView = itemView.findViewById(R.id.iv_country_flag)

        override fun bind(item: Country) {
            countryText.text = item.name
            Glide.with(itemView)
                .load(item.flag)
                .centerInside()
                .into(countryImage)
            materialCardView.setOnClickListener {
                viewModel.countryValue.postValue(item.code)
            }
        }
    }
}