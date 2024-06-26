package com.nulana.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nulana.littlelemon.ui.Home
import com.nulana.littlelemon.ui.Onboarding
import com.nulana.littlelemon.ui.Profile

@Composable
fun Navigation(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Consts.shared_creds.name, Context.MODE_PRIVATE)
    val isLoggedIn = sharedPreferences.getBoolean(Consts.logged_in.name, false);

    val startD = if (isLoggedIn) Profile.route else Onboarding.route
    NavHost(
        navController = navController, startDestination = startD
    ) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}