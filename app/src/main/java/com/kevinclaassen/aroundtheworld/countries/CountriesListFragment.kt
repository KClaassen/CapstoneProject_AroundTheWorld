package com.kevinclaassen.aroundtheworld.countries

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevinclaassen.aroundtheworld.R
import com.kevinclaassen.aroundtheworld.adapter.CountryAdapter
import com.kevinclaassen.aroundtheworld.databinding.FragmentCountriesListBinding
import com.kevinclaassen.aroundtheworld.models.Country
import kotlinx.android.synthetic.main.fragment_countries_list.*


//const val KEY_RECYCLER_STATE = "recycler_state"

class CountriesListFragment : Fragment(), CountryAdapter.CountryListener {

    private lateinit var binding: FragmentCountriesListBinding
//    private var listState: Parcelable? = null
//    private var bundleRecyclerViewState: Bundle? = null

    /**
     * Lazily initialize our [CountriesListViewModel].
     */
    private val viewModel: CountriesListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProvider(activity, CountriesListViewModel.Factory(activity.application)).get(
                CountriesListViewModel::class.java
        )
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Data Binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries_list, container, false)

        binding.viewmodel = viewModel

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        viewModel.countryListLiveData.observe(viewLifecycleOwner, Observer {
            processList(it)
        })

        fetchCountries()

        viewModel.navigateToCountry.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                this.findNavController().navigate(CountriesListFragmentDirections
                        .actionCountriesListFragmentToCountryDetailFragment(country))
                viewModel.onCountryNavigated()
            }
        })

        return binding.root
    }

    private fun fetchCountries(){
       viewModel.getDataFromRepo()
    }

    private fun processList(it: List<Country>?) {
        val countryAdapter = CountryAdapter(this)
        countries_recycler.layoutManager = LinearLayoutManager(requireContext())
        countries_recycler.adapter = countryAdapter
        countryAdapter.setData(it)
        progress_bar.visibility = View.GONE
    }

    override fun onClick(country: Country) {
        viewModel.onCountryClicked(country)
    }

    override fun onChecked(country: Country) {
        viewModel.updateCountry(country)
    }

//    override fun onPause() {
//        super.onPause()
//        bundleRecyclerViewState = Bundle()
//        listState = countries_recycler.layoutManager?.onSaveInstanceState()
//        bundleRecyclerViewState!!.putParcelable(KEY_RECYCLER_STATE, listState)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (bundleRecyclerViewState != null) {
//            Handler().postDelayed(Runnable {
//                listState = bundleRecyclerViewState!!.getParcelable(KEY_RECYCLER_STATE)
//                countries_recycler.getLayoutManager()?.onRestoreInstanceState(listState)
//            }, 50)
//        }
//
//        countries_recycler.layoutManager
//    }

}