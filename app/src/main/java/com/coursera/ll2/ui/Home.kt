package com.coursera.ll2.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.coursera.ll2.R
import com.coursera.ll2.db.MenuDatabase
import com.coursera.ll2.db.MenuItem
import com.coursera.ll2.network.MenuData
import com.coursera.ll2.network.MenuNetGetter
import com.coursera.ll2.ui.theme.Ll2Theme
import kotlinx.coroutines.launch

@Composable
fun Home(navController: NavController, database: MenuDatabase) {
    Column {
        val scope = rememberCoroutineScope()
        var menuOBJ: MenuData? by remember { mutableStateOf(null) }
        LaunchedEffect(true) {
            scope.launch {
                menuOBJ = try {
                    MenuNetGetter().getAsDataObj()
                } catch (e: Exception) {
                    Log.d("OBJ", e.localizedMessage)
                    null
                }
                if (menuOBJ != null) {
                    Log.d("OBJ", menuOBJ.toString())
                    if (database != null) (menuOBJ as MenuData).menu.forEach {
                        val count = database.menuDao().countID(it.id).value
                        Log.d("countID", "id " + it.id + " is " + count)
                        if (count == 0 || count == null) {
                            database.menuDao().saveMenuItem(it.getDBItem())
                        }
                    }
                }
            }
        }
        val menuItems by database.menuDao().getAllMenuItems().observeAsState(emptyList())
        Log.d("getAllMenuItems", menuItems.size.toString())
        Header(false, true, navController)
        RestDescription()
        Categories(menuItems)
        MenuList(menuItems)
    }
}

@Composable
fun RestDescription() {
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
fun Categories(menuItems: List<MenuItem>) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "ORDER FOR DELIVERY!",
            modifier = Modifier.padding(16.dp, 16.dp, 0.dp, 8.dp),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Row(Modifier.fillMaxWidth()) {
            val categories = menuItems.map { it.category }.toSet().toList()
            if (menuItems.isNotEmpty()) {
                LazyRow {
                    items(categories) { category ->
                        category(category)
                    }

                }
            } else category(name = "Loading...")
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
fun MenuList(menuItems: List<MenuItem>) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(menuItems) { item ->
            Divider(color = Color.LightGray)
            menuItem(item)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun menuItem(item: MenuItem) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
    ) {
        Text(
            text = item.title,
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
                    text = item.description,
                    modifier = Modifier.padding(16.dp, 0.dp),
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
                Text(
                    text = "$" + item.price,
                    modifier = Modifier.padding(16.dp, 0.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                )
            }
            GlideImage(
                model = item.image,
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
    Ll2Theme {
//        Home(rememberNavController(), database)
    }
}