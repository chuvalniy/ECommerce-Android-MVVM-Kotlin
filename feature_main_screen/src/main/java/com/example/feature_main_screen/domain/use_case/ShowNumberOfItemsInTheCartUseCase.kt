package com.example.feature_main_screen.domain.use_case

import com.example.feature_main_screen.domain.model.BasketDomain

class ShowNumberOfItemsInTheCartUseCase {

    operator fun invoke(basketInfo: List<BasketDomain>): Boolean {
        if (basketInfo.isEmpty()) {
            return false
        }

        return true
    }
}