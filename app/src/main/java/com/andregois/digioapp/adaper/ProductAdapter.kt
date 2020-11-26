package com.andregois.digioapp.adaper

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andregois.digioapp.R
import com.andregois.digioapp.domain.Product
import com.andregois.digioapp.domain.Spotlight
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.card_product.view.*
import kotlinx.android.synthetic.main.cars_spotlight.view.*

class ProductAdapter(private val productList: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var clickListener: ((item: Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (item: Product) -> Unit) {
        this.clickListener = listener
    }

    companion object {
        const val PRODUCT_WIDTH = 200
        const val PRODUCT_HEIGHT = 200
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productList[position]
        holder.bindItems(item)
        holder.imageView.setOnClickListener { clickListener?.invoke(item) }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    private fun removeItem(item: Product) {
        productList.remove(item)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val imageView: ImageView = itemView.productImage

        fun bindItems(item: Product) {
            Glide.with(itemView.context)
                .load(item.imageURL)
                .apply(RequestOptions().override(PRODUCT_WIDTH, PRODUCT_HEIGHT))
                .fitCenter()
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