package com.example.feature_home.presentation.home_screen.epoxy.model

import android.widget.ArrayAdapter
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_home.R
import com.example.feature_home.databinding.ModelTopBarBinding

class TopBarEpoxyModel : ViewBindingKotlinModel<ModelTopBarBinding>(R.layout.model_top_bar) {

    override fun ModelTopBarBinding.bind() {
        ArrayAdapter.createFromResource(
            root.context,
            R.array.locations,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_location_text_view)
            spinnerLocation.adapter = adapter
        }
    }
}