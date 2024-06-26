package com.nulana.littlelemon.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nulana.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Profile(navController: NavController) {
    Text(text = "i am profile")
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
        Profile(rememberNavController())
    }
}