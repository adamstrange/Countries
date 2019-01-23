package com.strangea.countries

import androidx.recyclerview.widget.DiffUtil
import com.strangea.countries.entities.Country

class CountryCallback : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return true //TODO implement if data changes or use data class
    }
}
