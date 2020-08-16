package me.sparker0i.ottcontent.view.composer.countrypicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_country_picker.*
import kotlinx.coroutines.launch
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.view.base.ScopedFragment
import me.sparker0i.ottcontent.view.composer.countrypicker.adapter.CountryAdapter
import me.sparker0i.ottcontent.viewmodel.ContentViewModel
import me.sparker0i.ottcontent.viewmodel.ContentViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CountryPickerFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private var mutableCountries = MutableLiveData<List<Country>>()

    private val viewModelFactory: ContentViewModelFactory by instance()
    private lateinit var viewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_picker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ContentViewModel::class.java)

        viewModel.countryValue.observe(viewLifecycleOwner, Observer { value ->
            if (value != null) {
                val action = CountryPickerFragmentDirections.countryPickerFragmentToPlatformPickerFragment(value)
                viewModel.countryValue.postValue(null)
                requireView().findNavController().navigate(action)
            }
        })

        bindUi()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, countries: List<Country>) {
        val adapter = CountryAdapter(viewModel)
        adapter.items = countries
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun bindUi() {
        mutableCountries.observe(viewLifecycleOwner, Observer {list ->
            setupRecyclerView(country_recycler_view, list)
        })

        getCountries()
    }

    fun getCountries() = launch {
        val countries = viewModel.countries.await()

        countries.observeForever{list ->
            mutableCountries.postValue(list)
        }
    }
}