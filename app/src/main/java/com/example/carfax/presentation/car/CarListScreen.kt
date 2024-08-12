package com.example.carfax.presentation.car

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.carfax.data.local_source.CarEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarListScreen(navController: NavHostController, viewModel: CarViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CARFAX", color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(Color(0xff3777bc))
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                when {
                    state.value.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    state.value.error != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = state.value.error ?: "Unknown error")
                        }
                    }
                    else -> {
                        CarList(list = state.value.carList, navController)
                    }
                }
            }
        }
    )
}


@Composable
fun CarList(list: List<CarEntity>, navController: NavHostController) {
    LazyColumn(modifier = Modifier.background(color = Color(0xFFf5f5f5))) {
        items(list) { listing ->
            CarListItem(listing, navController)
        }
    }
}


