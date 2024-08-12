package com.example.carfax

import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.carfax.data.local_source.CarEntity
import com.example.carfax.presentation.car.CarDetailScreen
import com.example.carfax.presentation.car.CarListScreen
import com.example.carfax.ui.theme.CarFaxTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarFaxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "car_list_screen"
                    )
                    {
                        composable(route = "car_list_screen")
                        {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CarListScreen(
                                    navController = navController,
                                )
                            }
                        }
                        composable(
                            route = "car_detail_screen/{jsonString}?image={image}",
                            arguments = listOf(
                                navArgument("jsonString") { type = NavType.StringType },
                                navArgument("image") { type = NavType.StringType }
                            )

                        ) { backStackEntry ->
                            val jsonString = backStackEntry.arguments?.getString("jsonString")
                            val image = backStackEntry.arguments?.getString("image")

                            if (jsonString != null) {
                                val decodedJsonString =
                                    URLDecoder.decode(jsonString, StandardCharsets.UTF_8.toString())
                                val listing =
                                    Gson().fromJson(decodedJsonString, CarEntity::class.java)
                                val imageUrl = String(Base64.decode(image, Base64.NO_WRAP))
                                CarDetailScreen(navController, listing, imageUrl)
                            }
                        }
                    }
                }
            }
        }
    }
}

