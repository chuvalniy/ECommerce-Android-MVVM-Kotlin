package com.example.ecommercialapplication.feature_main.presentation.main_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercialapplication.core.utils.ImageLoader
import com.example.ecommercialapplication.databinding.AdapterViewPagerHomeStoreItemBinding
import com.example.ecommercialapplication.feature_main.domain.model.HomeStore

class MainScreenViewPagerAdapter(private val hotSales: List<HomeStore>) :
    RecyclerView.Adapter<MainScreenViewPagerAdapter.HomeStoreViewHolder>() {

    private val infiniteScrollList: List<HomeStore> =
        listOf(hotSales.last()) + hotSales + listOf(hotSales.first())

    class HomeStoreViewHolder(
        private val binding: AdapterViewPagerHomeStoreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(homeStore: HomeStore) {
            binding.tvTitleHomeStore.text = homeStore.title
            binding.tvSubtitleHomeScreen.text = homeStore.subtitle
            binding.btnBuyNow.isVisible = homeStore.is_buy

            homeStore.is_new?.let {
                binding.tvNew.isVisible = true
            }

            ImageLoader.loadImage(homeStore.picture, binding.ivHotSales)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeStoreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeStoreViewHolder(
            AdapterViewPagerHomeStoreItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeStoreViewHolder, position: Int) {
        holder.bind(infiniteScrollList[position])
    }

    override fun getItemCount() = infiniteScrollList.size
}