package com.nulana.littlelemon.ui

import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nulana.littlelemon.Consts
import com.nulana.littlelemon.Home
import com.nulana.littlelemon.R
import com.nulana.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Onboarding(navController: NavController) {
    val context = LocalContext.current
    var firstName by remember { mutableStateOf(TextFieldValue(text = "")) }
    var lastName by remember { mutableStateOf(TextFieldValue(text = "")) }
    var email by remember { mutableStateOf(TextFieldValue(text = "")) }

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header()
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
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 24.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last name") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 24.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 24.dp)
            )
        }

        Button(
            onClick = regicterClick(firstName, lastName, email, navController, context),
            Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 16.dp),
            border = BorderStroke(1.dp, Color.Red),
            shape = RoundedCornerShape(14),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Red, containerColor = Color(240, 204, 26)
            ),
        ) {
            Text(text = "Register", color = Color.Black)
        }
    }
}

@Composable
private fun regicterClick(
    firstName: TextFieldValue,
    lastName: TextFieldValue,
    email: TextFieldValue,
    navController: NavController,
    context: Context
) = { ->
    if (firstName.text.isBlank()) Toast.makeText(
        context, "Registration unsuccessful. Please enter first name.", Toast.LENGTH_SHORT
    ).show()
    else if (lastName.text.isBlank()) Toast.makeText(
        context, "Registration unsuccessful. Please enter last name.", Toast.LENGTH_SHORT
    ).show()
    else if (email.text.isBlank()) Toast.makeText(
        context, "Registration unsuccessful. Please enter email.", Toast.LENGTH_SHORT
    ).show()
    else if (!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) Toast.makeText(
        context, "Registration unsuccessful. Please enter valid email.", Toast.LENGTH_SHORT
    ).show()
    else {
        Toast.makeText(
            context, "Registration successful!", Toast.LENGTH_SHORT
        ).show()

        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(Consts.shared_creds.name, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(Consts.user_first_name.name, firstName.text)
            .putString(Consts.user_last_name.name, lastName.text)
            .putString(Consts.user_email.name, email.text).putBoolean(Consts.logged_in.name, true)
            .apply();

        navController.navigate(Home.route)
    }
}

@Composable
fun Header() {
    Column(Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(90.dp)
        )
        Text(
            text = "Lets get to know you",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(72, 92, 75))
                .padding(16.dp)
                .height(90.dp)
                .wrapContentHeight(),
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    LittleLemonTheme {
        Onboarding(rememberNavController())
    }
}