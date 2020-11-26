package com.andregois.digioapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andregois.digioapp.R
import com.andregois.digioapp.adaper.SpotlightAdapter
import com.andregois.digioapp.data.MainViewModel
import com.andregois.digioapp.domain.Cash
import com.andregois.digioapp.domain.Product
import com.andregois.digioapp.domain.Spotlight
import com.andregois.digioapp.utils.Status
import com.andregois.digioapp.adaper.ProductAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cars_spotlight.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.response.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        setupSpotlightRV(response.spotlight)
                        setupProductRV(response.products)
                        setupCashImageView(response.cash)
                        progressBar.visibility = View.GONE
                    }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Não foi possível carregar seus dados", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupSpotlightRV(spotlights: List<Spotlight>) {
        spotlightList.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        spotlightList.adapter = SpotlightAdapter(spotlights)
    }

    private fun setupProductRV(products: List<Product>) {
        productList.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        productList.adapter = ProductAdapter(products.toMutableList())
    }

    private fun setupCashImageView(cash: Cash) {
        Glide.with(spotlightImage.context)
                .load(cash.bannerURL)
                .apply(RequestOptions().override(1000, 600))
                .into(spotlightImage)
    }

}