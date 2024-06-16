package com.ozalp.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ozalp.countries.R
import com.ozalp.countries.databinding.FragmentCountryBinding
import com.ozalp.countries.util.downloadFromUrl
import com.ozalp.countries.util.placeholderProgressBar
import com.ozalp.countries.viewmodel.CountryViewModel

class CountryFragment : Fragment() {

    private var countryUuid = 0
    private lateinit var viewModel: CountryViewModel
    private lateinit var dataBinding: FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryName = view.findViewById(R.id.countryName)
        countryCapital = view.findViewById(R.id.countryCapital)
        countryCurrency = view.findViewById(R.id.countryCurrency)
        countryLanguage = view.findViewById(R.id.countryLanguage)
        countryRegion = view.findViewById(R.id.countryRegion)
        countryImage = view.findViewById(R.id.countryImage)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->

            country?.let {
                dataBinding.selectedCountry = countryf
                /*
                countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countryCurrency.text = country.countryCurrency
                countryLanguage.text = country.countryLanguage
                countryRegion.text = country.countryRegion
                context?.let {
                    countryImage.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))
                }
                 */
            }
        })
    }

    private lateinit var countryName: TextView
    private lateinit var countryCapital: TextView
    private lateinit var countryCurrency: TextView
    private lateinit var countryLanguage: TextView
    private lateinit var countryRegion: TextView
    private lateinit var countryImage: ImageView
}