package me.sparker0i.ottcontent.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.viewmodel.OttContentViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var ottContentViewModel: OttContentViewModel
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ottContentViewModel = ViewModelProvider(this).get(OttContentViewModel::class.java)
        getCountries()
    }

    fun getCountries() {
        swipe_refresh.isRefreshing = false
        ottContentViewModel.countries.observe(this, Observer { list ->
            prepareRecyclerView(list)
        })
    }

    private fun prepareRecyclerView(countryList: List<Country>?) {
        countryAdapter = CountryAdapter(countryList)

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            country_recycler_view.layoutManager = LinearLayoutManager(this)
        else
            country_recycler_view.layoutManager = GridLayoutManager(this, 4)
        country_recycler_view.itemAnimator = DefaultItemAnimator()
        country_recycler_view.adapter = countryAdapter
    }
}