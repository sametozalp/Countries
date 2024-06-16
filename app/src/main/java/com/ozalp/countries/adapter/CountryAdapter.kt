package com.ozalp.countries.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ozalp.countries.R
import com.ozalp.countries.databinding.ItemCountryBinding
import com.ozalp.countries.model.Country
import com.ozalp.countries.util.downloadFromUrl
import com.ozalp.countries.util.placeholderProgressBar
import com.ozalp.countries.view.FeedFragmentDirections
import org.w3c.dom.Text

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {

    // View silip ItemCountryBinding yazdık
    class CountryViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_country, parent, false)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(
            inflater,
            R.layout.item_country,
            parent,
            false
        )
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        // artık data binding kullanıyoruz bunlara gerek kalmadı
        //holder.view.findViewById<TextView>(R.id.name).text = countryList[position].countryName
        //holder.view.findViewById<TextView>(R.id.region).text = countryList[position].countryRegion
        /*
        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.findViewById<ImageView>(R.id.imageView).downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.view.context))
        */

        holder.view.country = countryList[position]
        holder.view.listener = this
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val countryUUIDTextView = v.findViewById<TextView>(R.id.countryUUIDText)
        val uuid = countryUUIDTextView.text.toString().toInt()

        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}