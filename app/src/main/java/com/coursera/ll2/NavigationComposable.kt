package com.coursera.ll2

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.coursera.ll2.db.MenuDatabase
import com.coursera.ll2.ui.Home
import com.coursera.ll2.ui.Onboarding
import com.coursera.ll2.ui.Profile

@Composable
fun Navigation(navController: NavHostController, database: MenuDatabase) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Consts.shared_creds.name, Context.MODE_PRIVATE)
    val isLoggedIn = sharedPreferences.getBoolean(Consts.logged_in.name, false);

    val startD = if (isLoggedIn) Home.route else Onboarding.route
    NavHost(
        navController = navController, startDestination = startD
    ) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController, database)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}