package com.example.core.ui

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<DisplayableItem>() {
    override fun areItemsTheSame(oldItem: DisplayableItem, newItem: DisplayableItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: DisplayableItem, newItem: DisplayableItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}