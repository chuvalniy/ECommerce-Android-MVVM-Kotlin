package com.example.ecommercialapplication

import android.app.Application
import com.example.ecommercialapplication.di.coreModule
import com.example.feature_main.di.mainDataModule
import com.example.feature_main.di.mainPresentationModule
import com.example.feature_cart.di.cartDataModule
import com.example.feature_cart.di.cartDomainModule
import com.example.feature_cart.di.cartPresentationModule
import com.example.feature_details_screen.di.detailsDataModule
import com.example.feature_details_screen.di.detailsDomainModule
import com.example.feature_details_screen.di.detailsPresentationModule
import com.example.feature_home.di.homeScreenDataModule
import com.example.feature_home.di.homeScreenDomainModule
import com.example.feature_home.di.homeScreenPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ShopApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopApp)
            modules(
                coreModule,
                mainDataModule,
                mainPresentationModule,
                homeScreenDataModule,
                homeScreenDomainModule,
                homeScreenPresentationModule,
                detailsDataModule,
                detailsDomainModule,
                detailsPresentationModule,
                cartDataModule,
                cartDomainModule,
                cartPresentationModule
            )
        }
    }
}