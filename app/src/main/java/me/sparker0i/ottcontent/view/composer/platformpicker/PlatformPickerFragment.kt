package me.sparker0i.ottcontent.view.composer.platformpicker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_platform_picker.*
import kotlinx.coroutines.launch
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Platform
import me.sparker0i.ottcontent.view.base.ScopedFragment
import me.sparker0i.ottcontent.view.composer.countrypicker.adapter.CountryAdapter
import me.sparker0i.ottcontent.view.composer.platformpicker.adapter.PlatformAdapter
import me.sparker0i.ottcontent.viewmodel.ContentViewModel
import me.sparker0i.ottcontent.viewmodel.ContentViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class PlatformPickerFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private lateinit var country: String
    private var mutablePlatforms = MutableLiveData<List<Platform>>()

    val args: PlatformPickerFragmentArgs by navArgs()

    private val viewModelFactory: ContentViewModelFactory by instance()
    private lateinit var viewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_platform_picker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ContentViewModel::class.java)

        country = args.countryValue
        Log.i("Country Value", country)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ContentViewModel::class.java)

        viewModel.platformCountryValue.observe(viewLifecycleOwner, Observer { value ->
            if (value != null) {

            }
        })

        bindUi()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, platforms: List<Platform>) {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.itemAnimator = DefaultItemAnimator()
        val adapter = PlatformAdapter(viewModel)
        adapter.items = platforms
        println(platforms.size)
        recyclerView.adapter = adapter
    }

    private fun bindUi() {
        mutablePlatforms.observe(viewLifecycleOwner, Observer { list ->
            setupRecyclerView(platform_recycler_view, list)
        })

        getPlatforms()
    }

    private fun getPlatforms() = launch {
        val platforms = viewModel.platforms(country).await()

        platforms.observeForever{list ->
            mutablePlatforms.postValue(list)
        }
    }
}