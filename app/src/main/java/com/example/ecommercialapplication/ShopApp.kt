package com.example.ecommercialapplication

import android.app.Application
import com.example.data_user_session.di.userSessionDataModule
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
import com.example.feature_search.di.searchDataModule
import com.example.feature_search.di.searchDomainModule
import com.example.feature_search.di.searchPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ShopApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopApp)
            modules(
                coreModule,
                userSessionDataModule,
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
                cartPresentationModule,
                searchDataModule,
                searchDomainModule,
                searchPresentationModule
            )
        }
    }
}