package com.nulana.littlelemon.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nulana.littlelemon.Consts
import com.nulana.littlelemon.Onboarding
import com.nulana.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Consts.shared_creds.name, Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString(Consts.user_first_name.name, "")
    val lastName = sharedPreferences.getString(Consts.user_last_name.name, "")
    val email = sharedPreferences.getString(Consts.user_email.name, "")

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header(false)
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Personal information",
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            OutlinedTextField(
                value = firstName!!,
                onValueChange = { },
                readOnly = true,
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 24.dp)
            )
            OutlinedTextField(
                value = lastName!!,
                onValueChange = { },
                readOnly = true,
                label = { Text("Last name") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 24.dp)
            )
            OutlinedTextField(
                value = email!!,
                onValueChange = { },
                readOnly = true,
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 24.dp)
            )
        }

        Button(
            onClick = regicterClick(navController, context),
            Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 16.dp),
            border = BorderStroke(1.dp, Color.Red),
            shape = RoundedCornerShape(14),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Red, containerColor = Color(240, 204, 26)
            ),
        ) {
            Text(text = "Log out", color = Color.Black)
        }
    }
}

@Composable
private fun regicterClick(
    navController: NavController, context: Context
) = { ->
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Consts.shared_creds.name, Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean(Consts.logged_in.name, false).apply();

    navController.navigate(Onboarding.route)
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
        Profile(rememberNavController())
    }
}