package com.example.llyoddemoproject.di

import com.example.llyoddemoproject.data.network.ApiHelper
import com.example.llyoddemoproject.data.network.AppApiHelper
import com.example.llyoddemoproject.data.prefs.AppPreferencesHelper
import com.example.llyoddemoproject.data.prefs.PreferencesHelper
import com.example.llyoddemoproject.ui.base.BaseRepository
import com.example.llyoddemoproject.data.repos.DashboardRepository
import com.example.llyoddemoproject.util.PREF_NAME
import com.example.llyoddemoproject.util.coroutines.AppDispatcherProvider
import com.example.llyoddemoproject.util.coroutines.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object{
        @Provides
        @PreferenceInfo
        fun providePreferenceName(): String {
            return PREF_NAME
        }

        @Provides
        @EmptyString
        fun provideApiKey(): String {
            return ""
        }

    }

    @Binds
    @Singleton
    abstract fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper

    @Binds
    @Singleton
    abstract fun providePreferenceHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper


    @Binds
    @Singleton
    abstract fun provideDispatcher(dispatcherProvider: AppDispatcherProvider): DispatcherProvider

    @Binds
    @Singleton
    abstract fun provideDashboardRepo(dashboardRepository: DashboardRepository): BaseRepository

}