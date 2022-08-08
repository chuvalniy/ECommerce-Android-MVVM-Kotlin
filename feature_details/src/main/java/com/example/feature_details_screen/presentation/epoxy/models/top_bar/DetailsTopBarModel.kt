package com.example.feature_details_screen.presentation.epoxy.models.top_bar

import android.widget.ImageButton
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R

@EpoxyModelClass
abstract class DetailsTopBarModel : EpoxyModelWithHolder<DetailsTopBarModel.TopBarHolder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onGoBackClick: () -> Unit

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onGoToCartClick: () -> Unit

    override fun bind(holder: TopBarHolder) {
        holder.buttonGoBack.setOnClickListener { onGoBackClick() }
        holder.buttonGoToCart.setOnClickListener { onGoToCartClick() }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.details_top_bar
    }

    class TopBarHolder : KotlinEpoxyHolder() {
        val buttonGoBack by bind<ImageButton>(R.id.btnGoBack)
        val buttonGoToCart by bind<ImageButton>(R.id.btnCart)
    }
}

