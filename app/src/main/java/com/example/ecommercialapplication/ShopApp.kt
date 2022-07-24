package com.example.ecommercialapplication

import android.app.Application
import com.example.core.coreModule
import com.example.feature_cart.di.cartScreenModule
import com.example.feature_details_screen.di.detailsDataModule
import com.example.feature_details_screen.di.detailsDomainModule
import com.example.feature_details_screen.di.detailsPresentationModule
import com.example.feature_main_screen.di.mainScreenDataModule
import com.example.feature_main_screen.di.mainScreenDomainModule
import com.example.feature_main_screen.di.mainScreenPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ShopApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopApp)
            modules(
                coreModule,
                mainScreenDataModule,
                mainScreenDomainModule,
                mainScreenPresentationModule,
                detailsDataModule,
                detailsDomainModule,
                detailsPresentationModule,
                cartScreenModule
            )
        }
    }
}