package me.sparker0i.ottcontent.view.composer.platformpicker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Platform
import me.sparker0i.ottcontent.view.base.CardAdapter
import me.sparker0i.ottcontent.viewmodel.ContentViewModel

internal class PlatformAdapter(vm: ContentViewModel) : CardAdapter<Platform>(R.layout.item_platform) {
    override lateinit var items: List<Platform>
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

    internal inner class ViewHolder(itemView: View): ItemViewHolder<Platform>(itemView) {
        private val materialCardView: MaterialCardView = itemView.findViewById(R.id.item_platform_card)
        private val platformText: TextView = itemView.findViewById(R.id.tv_platform_name)
        private val platformIcon: ImageView = itemView.findViewById(R.id.iv_platform_icon)

        override fun bind(item: Platform) {
            platformText.text = item.localizedName
            Glide.with(itemView)
                .load(item.icon)
                .centerInside()
                .into(platformIcon)
            materialCardView.setOnClickListener {
                viewModel.platformCountryValue.postValue(Pair(item.countryCode, item.platformId))
            }
        }
    }
}