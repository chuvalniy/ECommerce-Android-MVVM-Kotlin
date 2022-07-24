package com.example.feature_main_screen.presentation.main_screen.epoxy.model

import android.widget.ArrayAdapter
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ModelTopBarBinding

class TopBarEpoxyModel() : ViewBindingKotlinModel<ModelTopBarBinding>(R.layout.model_top_bar) {

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