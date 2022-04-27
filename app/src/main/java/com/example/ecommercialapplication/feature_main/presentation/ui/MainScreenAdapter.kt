package com.example.ecommercialapplication.feature_main.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercialapplication.core.utils.ImageLoader
import com.example.ecommercialapplication.databinding.AdapterRecyclerViewBestSellerItemBinding
import com.example.ecommercialapplication.feature_main.domain.model.BestSeller

class MainScreenAdapter :
    ListAdapter<BestSeller, MainScreenAdapter.MainScreenViewHolder>(DiffCallback) {

    class MainScreenViewHolder(
        private val binding: AdapterRecyclerViewBestSellerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bestSeller: BestSeller) {
            binding.apply {
                tvDefaultPrice.text = "$${bestSeller.price_without_discount}"
                tvDiscountPrice.text = "$${bestSeller.discount_price}"
                tvTitle.text = bestSeller.title
            }
            ImageLoader.loadImage(bestSeller.picture, binding.ivBestSeller)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MainScreenViewHolder(
            AdapterRecyclerViewBestSellerItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BestSeller>() {
        override fun areItemsTheSame(oldItem: BestSeller, newItem: BestSeller): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BestSeller, newItem: BestSeller): Boolean {
            return oldItem.id == newItem.id
        }

    }
}