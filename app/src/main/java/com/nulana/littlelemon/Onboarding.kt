package com.nulana.littlelemon

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nulana.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Onboarding() {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header()
        Form()
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 16.dp),
            border = BorderStroke(1.dp, Color.Red),
            shape = RoundedCornerShape(14),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Red,
                containerColor = Color(240, 204, 26)
            ),
        ) {
            Text(text = "Register", color = Color.Black)
        }
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

@Composable
fun Form() {
    var text1 by remember { mutableStateOf(TextFieldValue(text = "")) }
    var text2 by remember { mutableStateOf(TextFieldValue(text = "")) }
    var text3 by remember { mutableStateOf(TextFieldValue(text = "")) }

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
            value = text1,
            onValueChange = { text1 = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 24.dp)
        )
        OutlinedTextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text("Last name") },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 24.dp)
        )
        OutlinedTextField(
            value = text3,
            onValueChange = { text3 = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 24.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    LittleLemonTheme {
        Onboarding()
    }
}