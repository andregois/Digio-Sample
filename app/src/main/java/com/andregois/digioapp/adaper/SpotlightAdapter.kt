package com.andregois.digioapp.adaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andregois.digioapp.R
import com.andregois.digioapp.domain.Spotlight
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SpotlightAdapter(private val spotlightList: List<Spotlight>) :
    RecyclerView.Adapter<SpotlightAdapter.ViewHolder>() {

    companion object {
        const val SPOTLIGHT_WIDTH = 950
        const val SPOTLIGHT_HEIGHT = 600
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cars_spotlight, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(spotlightList[position])
    }

    override fun getItemCount(): Int {
        return spotlightList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: Spotlight) {
            val imageView = itemView.findViewById<ImageView>(R.id.spotlightImage)
            Glide.with(itemView.context)
                .load(item.bannerURL)
                .apply(RequestOptions().override(SPOTLIGHT_WIDTH, SPOTLIGHT_HEIGHT))
                .into(imageView)
        }
    }
}