package com.example.ecommercialapplication.feature_main.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercialapplication.core.utils.ImageLoader
import com.example.ecommercialapplication.databinding.AdapterViewPagerHomeStoreItemBinding
import com.example.ecommercialapplication.feature_main.domain.model.HomeStore

class HomeStoreViewPagerAdapter :
    ListAdapter<HomeStore, HomeStoreViewPagerAdapter.HomeStoreViewHolder>(StoreDiffCallback) {


    class HomeStoreViewHolder(
        private val binding: AdapterViewPagerHomeStoreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(homeStore: HomeStore) {
            binding.apply {
                tvTitleHomeStore.text = homeStore.title
                tvSubtitleHomeScreen.text = homeStore.subtitle
            }
            homeStore.is_new?.let {
                binding.tvNew.isVisible = true
            }
            if (homeStore.is_buy) {
                binding.btnBuyNow.isVisible = true
            } else {
                binding.btnBuyNow.isInvisible = true
            }
            ImageLoader.loadImage(homeStore.picture, binding.ivHotSales)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeStoreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeStoreViewHolder(
            AdapterViewPagerHomeStoreItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeStoreViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object StoreDiffCallback : DiffUtil.ItemCallback<HomeStore>() {
        override fun areItemsTheSame(oldItem: HomeStore, newItem: HomeStore): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HomeStore, newItem: HomeStore): Boolean {
            return oldItem.id == newItem.id
        }
    }

}