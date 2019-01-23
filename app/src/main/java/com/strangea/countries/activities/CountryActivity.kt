package com.strangea.countries.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.strangea.countries.BuildConfig
import com.strangea.countries.R
import com.strangea.countries.entities.Country
import kotlinx.android.synthetic.main.activity_country.*
import kotlinx.android.synthetic.main.content_country.*
import java.lang.StringBuilder

class CountryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        //TODO use databinding library
        val country = Gson().fromJson(intent.getStringExtra(COUNTRY), Country::class.java)

        supportActionBar?.title = country.name
        country.alpha2Code?.let {
            Picasso.get().load(String.format(BuildConfig.FLAG_URL, it.toLowerCase())).into(flagImageView)
        }

        val stringBuilder = StringBuilder()

        stringBuilder.append("Native name: ").append(country.nativeName).append("\n")
        stringBuilder.append("Population: ").append(country.population.toString()).append("\n")
        stringBuilder.append("Capital: ").append(country.capital).append("\n")
        stringBuilder.append("Region: ").append(country.region).append("\n")
        stringBuilder.append("Sub region: ").append(country.subregion).append("\n")

        contentTextView.text = stringBuilder.toString()
    }

    companion object {
        const val COUNTRY = "country"
    }
}
