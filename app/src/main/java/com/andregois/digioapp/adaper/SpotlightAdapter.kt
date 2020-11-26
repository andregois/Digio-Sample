package com.andregois.digioapp.adaper

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andregois.digioapp.R
import com.andregois.digioapp.domain.Spotlight
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.cars_spotlight.view.*

class SpotlightAdapter(private val spotlightList: MutableList<Spotlight>) :
    RecyclerView.Adapter<SpotlightAdapter.ViewHolder>() {

    private var clickListener: ((item: Spotlight) -> Unit)? = null

    fun setOnItemClickListener(listener: (item: Spotlight) -> Unit) {
        this.clickListener = listener
    }


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
        val item = spotlightList[position]
        holder.bindItems(item)
        holder.imageView.setOnClickListener { clickListener?.invoke(item) }
    }

    private fun showDesciption() {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return spotlightList.size
    }

    private fun removeItem(item: Spotlight) {
        spotlightList.remove(item)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val imageView: ImageView = itemView.spotlightImage

        fun bindItems(item: Spotlight) {

            Glide.with(itemView.context)
                .load(item.bannerURL)
                .apply(RequestOptions().override(SPOTLIGHT_WIDTH, SPOTLIGHT_HEIGHT))
                .placeholder(R.drawable.logo)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        removeItem(item)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .into(imageView)
        }
    }
}