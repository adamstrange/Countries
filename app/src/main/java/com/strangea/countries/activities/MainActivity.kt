package com.strangea.countries.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.strangea.countries.entities.Country
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.snackbar.Snackbar
import com.strangea.countries.CountryAdapter
import com.strangea.countries.CountryCallback
import com.strangea.countries.CountryViewModel
import com.strangea.countries.R
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val countryAdapter = CountryAdapter(CountryCallback())
        recyclerView.adapter = countryAdapter

        val countryObserver = Observer<Response<List<Country>>> { response ->
            if(response.isSuccessful) {
                countryAdapter.submitList(response.body())
            } else {
                Snackbar.make(recyclerView, response.message(), Snackbar.LENGTH_LONG).show()
            }
        }

        val viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.countryList.observe(this, countryObserver)

        viewModel.loadCountries()
    }

}
