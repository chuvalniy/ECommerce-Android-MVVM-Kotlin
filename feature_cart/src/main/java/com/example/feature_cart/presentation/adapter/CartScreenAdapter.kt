package com.example.feature_cart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_cart.R
import com.example.feature_cart.databinding.CartItemBinding
import com.example.feature_cart.domain.model.BasketDomain

class CartScreenAdapter(
    private val glide: RequestManager
) : ListAdapter<BasketDomain, CartScreenAdapter.CastScreenViewHolder>(CartDiffCallback) {

    class CastScreenViewHolder(
        private val binding: CartItemBinding,
        private val glide: RequestManager
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(basketDomain: BasketDomain) {
            binding.tvPrice.text = this.itemView.context.getString(R.string.product_price, basketDomain.price.toInt())
            binding.tvTitle.text = basketDomain.title
            glide.load(basketDomain.images).into(binding.ivCartProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastScreenViewHolder {
        return CastScreenViewHolder(
            CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            glide
        )
    }

    override fun onBindViewHolder(holder: CastScreenViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    companion object CartDiffCallback : DiffUtil.ItemCallback<BasketDomain>() {
        override fun areItemsTheSame(oldItem: BasketDomain, newItem: BasketDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BasketDomain, newItem: BasketDomain): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

}