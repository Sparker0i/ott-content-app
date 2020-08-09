package me.sparker0i.ottcontent.view.countrypicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.view.base.ScopedFragment
import me.sparker0i.ottcontent.view.countrypicker.adapter.CountryAdapter
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
    private lateinit var selectionTracker: SelectionTracker<Long?>

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

    private fun setupRecyclerView(recyclerView: RecyclerView, countries: List<Country>) {
        val adapter = CountryAdapter()
        adapter.items = countries
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.itemAnimator = DefaultItemAnimator()

        selectionTracker = SelectionTracker.Builder(
            "country_selection",
            recyclerView,
            CountryAdapter.KeyProvider(adapter),
            CountryAdapter.DetailsLookup(recyclerView),
            StorageStrategy.createLongStorage()
        )
            .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
            .build()

        adapter.selectionTracker = selectionTracker
    }

    private fun bindUi() {
        mutableCountries.observe(viewLifecycleOwner, Observer {list ->
            setupRecyclerView(country_recycler_view, list)
        })

        getCountries()
    }

    fun getCountries() = launch {
        val countries = viewModel.countries.await()

        countries.observe(viewLifecycleOwner, Observer {list ->
            mutableCountries.postValue(list)
        })
    }
}