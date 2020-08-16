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
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.model.Platform
import me.sparker0i.ottcontent.viewmodel.ContentViewModel

internal class PlatformAdapter(vm: ContentViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var items: List<Platform>
    private val viewModel = vm

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_platform, parent, false)
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
        private val materialCardView: MaterialCardView = itemView.findViewById(R.id.item_platform_card)
        private val platformText: TextView = itemView.findViewById(R.id.tv_platform_name)
        private val platformIcon: ImageView = itemView.findViewById(R.id.iv_platform_icon)

        fun bind(platform: Platform) {
            platformText.text = platform.localizedName
            Glide.with(itemView)
                .load(platform.icon)
                .centerInside()
                .into(platformIcon)
            materialCardView.setOnClickListener {
                viewModel.platformCountryValue.postValue(Pair(platform.countryCode, platform.platformId))
            }
        }
    }
}