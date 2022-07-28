package com.example.feature_main_screen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HotSalesDto(
    val id: Int,
    @SerializedName("is_buy")
    val isBuy: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean?,
    val picture: String,
    val subtitle: String,
    val title: String
)