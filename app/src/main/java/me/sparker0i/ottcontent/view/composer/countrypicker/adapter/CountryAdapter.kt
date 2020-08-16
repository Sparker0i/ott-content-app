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
import me.sparker0i.ottcontent.viewmodel.ContentViewModel

internal class CountryAdapter(vm: ContentViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var items: List<Country>
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
        (viewHolder as ItemViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val materialCardView: MaterialCardView = itemView.findViewById(R.id.item_country_card)
        private val countryText: TextView = itemView.findViewById(R.id.tv_country_name)
        private val countryImage: ImageView = itemView.findViewById(R.id.iv_country_flag)

        fun bind(country: Country) {
            countryText.text = country.name
            Glide.with(itemView)
                .load(country.flag)
                .centerInside()
                .into(countryImage)
            materialCardView.setOnClickListener {
                viewModel.countryValue.postValue(country.code)
            }
        }
    }
}