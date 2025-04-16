package com.nikol.presentation.di

import com.nikol.navigaiton.BottomBarItem
import com.nikol.navigaiton.FeatureApi
import com.nikol.presentation.navigation.MainFeatureBottomBarItem
import com.nikol.presentation.navigation.MainFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class MainFeatureModule {
    @Provides
    @IntoSet
    fun provideAuthFeatureNavigationApi() : FeatureApi {
        return MainFeatureImpl()
    }

    @Provides
    @IntoSet
    fun provideMainFeatureBottomBarItem() : BottomBarItem {
        return MainFeatureBottomBarItem()
    }
}