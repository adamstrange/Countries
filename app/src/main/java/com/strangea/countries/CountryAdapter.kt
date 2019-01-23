package com.strangea.countries

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.strangea.countries.activities.CountryActivity
import com.strangea.countries.entities.Country
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_country.*

class CountryAdapter constructor(diffCallback: DiffUtil.ItemCallback<Country>) :
    ListAdapter<Country, CountryAdapter.CountryViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_country, parent, false))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class CountryViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer,

        View.OnClickListener {
        override fun onClick(clickedView: View) {
            val intent = Intent(clickedView.context, CountryActivity::class.java)
            intent.putExtra(CountryActivity.COUNTRY, Gson().toJson(getItem(adapterPosition))) //TODO instead of using gson to serialise use @Parcelable
            clickedView.context.startActivity(intent)
        }

        internal fun bindTo(country: Country) {
            nameTextView.text = country.name
            country.alpha2Code?.let {
                Picasso.get().load(String.format(BuildConfig.FLAG_URL, it.toLowerCase())).into(flagImageView)
            }
            itemView.setOnClickListener(this)
        }
    }
}