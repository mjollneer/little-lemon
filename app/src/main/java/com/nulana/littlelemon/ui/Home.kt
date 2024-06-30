package com.nulana.littlelemon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nulana.littlelemon.R
import com.nulana.littlelemon.network.MenuGetter
import com.nulana.littlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.launch

@Composable
fun Home(navController: NavController) {
    Column {
        val scope = rememberCoroutineScope()
        var text by remember { mutableStateOf("Loading") }
        LaunchedEffect(true) {
            scope.launch {
                text = try {
                    MenuGetter().getAsText()
                } catch (e: Exception) {
                    e.localizedMessage ?: "error"
                }
            }
        }

        Header(false, true, navController)

        restDescription()
        categories()
        menuList()
//        Text(text = text)
    }
}

@Composable
fun restDescription() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color(72, 92, 75))
    ) {
        Text(
            text = "Little Lemon",
            modifier = Modifier.padding(16.dp, 0.dp),
            fontSize = 36.sp,
            color = Color(240, 204, 26),
        )
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier.fillMaxWidth(0.6f)
            ) {
                Text(
                    text = "Belgrade",
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 8.dp),
                    fontSize = 24.sp,
                    color = Color.White,
                )
                Text(
                    text = "For a taste of Serbia's rich coffee culture in Belgrade, visit one of the city's renowned cafes where you can savor aromatic, strong brews amidst a vibrant and historic atmosphere.",
                    modifier = Modifier.padding(16.dp, 0.dp),
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hrana),
                contentDescription = "",
                modifier = Modifier
                    .width(180.dp)
                    .height(180.dp)
                    .padding(16.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    ),
                contentScale = ContentScale.Crop,

                )
        }
        //https://stackoverflow.com/questions/75541072/how-can-i-make-this-custom-text-search-field-in-jetpack-compose
        TextField(
            value = "",
            onValueChange = { },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = { Text(text = "Enter search phrase") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun categories() {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "ORDER FOR DELIVERY!",
            modifier = Modifier.padding(16.dp, 16.dp, 0.dp, 8.dp),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Row(Modifier.fillMaxWidth()) {
            category(name = "name 1")
            category(name = "name 2")
            category(name = "name 3")
        }
    }

}

@Composable
fun category(name: String) {
    Button(
        onClick = { /*TODO*/ },
        Modifier.padding(8.dp),
        shape = RoundedCornerShape(32),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Black, containerColor = Color(234, 234, 234)
        ),
    ) {
        Text(
            text = name.toUpperCase(),
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun menuList() {
    Column(Modifier.fillMaxWidth()) {
        menuItem()
        menuItem()
        menuItem()
    }
}

@Composable
fun menuItem() {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Name",
            modifier = Modifier.padding(16.dp, 0.dp),
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier.fillMaxWidth(0.7f)
            ) {
                Text(
                    text = "Description",
                    modifier = Modifier.padding(16.dp, 0.dp),
                    fontSize = 14.sp,
                    color = Color.LightGray,
                )
                Text(
                    text = "Price",
                    modifier = Modifier.padding(16.dp, 0.dp),
                    fontSize = 16.sp,
                    color = Color.Gray,
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hrana),
                contentDescription = "",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.Crop,
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
        Home(rememberNavController())
    }
}