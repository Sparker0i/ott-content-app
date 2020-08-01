package me.sparker0i.ottcontent.view.countrypicker

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.view.CountryAdapter
import me.sparker0i.ottcontent.view.base.ScopedFragment
import me.sparker0i.ottcontent.viewmodel.ContentViewModel
import me.sparker0i.ottcontent.viewmodel.ContentViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CountryPickerFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: ContentViewModelFactory by instance()
    private lateinit var viewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ContentViewModel::class.java)

        bindUi()
    }

    private fun bindUi() {
        getCountries()
    }

    fun getCountries() = launch {
        val countries = viewModel.countries.await()

        countries.observe(viewLifecycleOwner, Observer {list ->
            prepareRecyclerView(list)
        })
    }

    private fun prepareRecyclerView(countryList: List<Country>?) {
        val countryAdapter = CountryAdapter(countryList)

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            country_recycler_view.layoutManager = LinearLayoutManager(context)
        else
            country_recycler_view.layoutManager = GridLayoutManager(context, 4)
        country_recycler_view.itemAnimator = DefaultItemAnimator()
        country_recycler_view.adapter = countryAdapter
    }
}