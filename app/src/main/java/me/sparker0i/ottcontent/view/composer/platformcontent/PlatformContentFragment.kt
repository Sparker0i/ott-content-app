package me.sparker0i.ottcontent.view.composer.platformcontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.model.Show
import me.sparker0i.ottcontent.view.base.ScopedFragment
import me.sparker0i.ottcontent.viewmodel.ContentViewModel
import me.sparker0i.ottcontent.viewmodel.ContentViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class PlatformContentFragment: ScopedFragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()

    private lateinit var country: String
    private var platformId: Int = -1

    val args: PlatformContentFragmentArgs by navArgs()

    private var mutableContent = MutableLiveData<List<Show>>()

    private val viewModelFactory: ContentViewModelFactory by instance()
    private lateinit var viewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_platform_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ContentViewModel::class.java)

        country = args.countryValue
        platformId = args.platformValue

        println("$country $platformId")
    }
}