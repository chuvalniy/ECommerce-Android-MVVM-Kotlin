package com.example.feature_details_screen.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_details_screen.databinding.ViewPagerItemBinding

class DetailsScreenViewPagerAdapter(
    private val glide: RequestManager
) : ListAdapter<String, DetailsScreenViewPagerAdapter.DetailScreenViewHolder>(DetailsDiffCallback) {
    class DetailScreenViewHolder(
        private val binding: ViewPagerItemBinding,
        private val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind (string: String) {
            glide.load(string).into(binding.ivProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailScreenViewHolder {
        return DetailScreenViewHolder(
            ViewPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            glide
        )
    }

    override fun onBindViewHolder(holder: DetailScreenViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DetailsDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

}