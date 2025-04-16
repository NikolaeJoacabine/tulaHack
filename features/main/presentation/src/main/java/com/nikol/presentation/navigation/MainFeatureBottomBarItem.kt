package com.nikol.presentation.navigation

import com.nikol.navigaiton.BottomBarItem
import com.nikol.presentation.R

class MainFeatureBottomBarItem : BottomBarItem {
    override val navigationRoute: String = MainFeaturesScreens.NAVIGATION_ROUTE
    override val nameId: Int = R.string.main
    override val iconId: Int = R.drawable.home
}