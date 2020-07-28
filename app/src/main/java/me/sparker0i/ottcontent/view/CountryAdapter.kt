package me.sparker0i.ottcontent.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Country

class CountryAdapter(val countryList: List<Country>?): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context

        return ViewHolder(LayoutInflater.from(context).inflate(
            R.layout.item_country,
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return countryList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countryList!!.get(position)

        holder.textViewTitle.text = country.name
        holder.textViewTitle.setOnClickListener{
            Log.i("Pressed", "Position ${position}, Code ${country.code}, Name ${country.name}")
        }
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewTitle: TextView = itemView.findViewById(R.id.text_country_title);
    }
}