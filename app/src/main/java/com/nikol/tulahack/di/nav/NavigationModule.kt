package com.nikol.tulahack.di.nav

import com.nikol.navigaiton.BottomBarItem
import com.nikol.navigaiton.FeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideFeatureApiList(featureApis: Set<@JvmSuppressWildcards FeatureApi>): List<FeatureApi> {
        return featureApis.toList()
    }

    @Provides
    @Singleton
    fun provideBottomBarItemList(bottomBarItems: Set<@JvmSuppressWildcards BottomBarItem>): List<BottomBarItem> {
        return bottomBarItems.toList()
    }
}