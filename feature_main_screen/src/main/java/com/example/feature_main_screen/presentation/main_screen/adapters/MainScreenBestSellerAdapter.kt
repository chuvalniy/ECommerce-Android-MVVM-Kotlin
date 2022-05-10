package com.example.feature_main_screen.presentation.main_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_main_screen.databinding.AdapterRecyclerViewBestSellerItemBinding
import com.example.feature_main_screen.domain.model.BestSeller

class MainScreenBestSellerAdapter(
    private val glide: RequestManager
) : ListAdapter<BestSeller, MainScreenBestSellerAdapter.BestSellerViewHolder>(DiffCallbackBestSeller) {

    class BestSellerViewHolder(
        private val binding: AdapterRecyclerViewBestSellerItemBinding,
        private val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bestSeller: BestSeller) {
            binding.tvDefaultPrice.text = "$${bestSeller.price_without_discount}"
            binding.tvDiscountPrice.text = "$${bestSeller.discount_price}"
            binding.tvTitle.text = bestSeller.title
            binding.btnAddToFavorites.isChecked = bestSeller.is_favorites

            glide.load(bestSeller.picture).into(binding.ivBestSeller)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BestSellerViewHolder(
            AdapterRecyclerViewBestSellerItemBinding.inflate(layoutInflater, parent, false),
            glide
        )
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallbackBestSeller : DiffUtil.ItemCallback<BestSeller>() {
        override fun areItemsTheSame(oldItem: BestSeller, newItem: BestSeller): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BestSeller, newItem: BestSeller): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}