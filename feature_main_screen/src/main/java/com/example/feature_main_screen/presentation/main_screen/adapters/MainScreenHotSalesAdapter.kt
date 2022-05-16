//package com.example.feature_main_screen.presentation.main_screen.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.core.view.isVisible
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.RequestManager
//import com.example.feature_main_screen.databinding.AdapterViewPagerHomeStoreItemBinding
//import com.example.feature_main_screen.domain.model.HomeStoreDomain
//
//class MainScreenHotSalesAdapter(
//    private val glide: RequestManager,
//) : ListAdapter<HomeStoreDomain, MainScreenHotSalesAdapter.HomeStoreViewHolder>(DiffCallbackHomeStore) {
//
//    class HomeStoreViewHolder(
//        private val binding: AdapterViewPagerHomeStoreItemBinding,
//        private val glide: RequestManager,
//
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(homeStore: HomeStoreDomain) {
//            binding.tvTitleHomeStore.text = homeStore.title
//            binding.tvSubtitleHomeScreen.text = homeStore.subtitle
//            binding.btnBuyNow.isVisible = homeStore.is_buy
//
//            homeStore.is_new?.let { isNew ->
//                binding.tvNew.isVisible = isNew
//            }
//
//            glide.load(homeStore.picture).into(binding.ivHotSales)
//        }
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): HomeStoreViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        return HomeStoreViewHolder(
//            AdapterViewPagerHomeStoreItemBinding.inflate(layoutInflater, parent, false),
//            glide
//        )
//    }
//
//    override fun onBindViewHolder(holder: HomeStoreViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item)
//    }
//
//    companion object DiffCallbackHomeStore : DiffUtil.ItemCallback<HomeStoreDomain>() {
//        override fun areItemsTheSame(oldItem: HomeStoreDomain, newItem: HomeStoreDomain): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: HomeStoreDomain, newItem: HomeStoreDomain): Boolean {
//            return oldItem.hashCode() == newItem.hashCode()
//        }
//
//    }
//}