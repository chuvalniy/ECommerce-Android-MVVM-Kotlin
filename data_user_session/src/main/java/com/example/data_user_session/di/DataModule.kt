package com.example.data_user_session.di

import com.example.data_user_session.data.UserSessionStorage
import com.example.data_user_session.data.UserSessionStorageImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val userSessionDataModule = module {
    single<UserSessionStorage> {
        UserSessionStorageImpl(context = androidContext())
    }
}