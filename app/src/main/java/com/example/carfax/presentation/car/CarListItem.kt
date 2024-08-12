package com.example.carfax.presentation.car

import android.Manifest
import android.content.pm.PackageManager
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.carfax.presentation.util.CarUtils
import com.example.carfax.R
import com.example.carfax.data.local_source.CarEntity
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun CarListItem(listing: CarEntity, navController: NavHostController) {

    val context = LocalContext.current
    val phoneNumber = listing.phone

    var hasCallPermission by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCallPermission = isGranted
        if (isGranted) {
            CarUtils.startCall(context, phoneNumber)
        }
    }

    LaunchedEffect(key1 = true) {
        hasCallPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED
    }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                val json = Gson().toJson(listing)
                val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
                val image = listing.imageUrl
                val encodedImageUrl = Base64.encodeToString(image.toByteArray(), Base64.NO_WRAP)
                navController.navigate("car_detail_screen/$encodedJson?image=$encodedImageUrl")
            }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(listing.imageUrl)
                    .placeholder(R.drawable.car_placeholder)
                    .error(R.drawable.car_placeholder)
                    .build(),
                contentDescription = "Car Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${listing.year} ${listing.make} ${listing.model} ${listing.trim}",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 6.dp)
            )


            Row(
                modifier = Modifier.padding(bottom = 6.dp, start = 10.dp)
            ) {
                Text(
                    text = "${listing.currentPrice}",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "|",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${CarUtils.formatMileage(listing.mileage)} mi",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
                )
            }

            Text(
                text = "${listing.address}",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
            )

            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )

            Button(
                onClick = {  if (hasCallPermission) {
                    CarUtils.startCall(context, phoneNumber)
                } else {
                    launcher.launch(Manifest.permission.CALL_PHONE)
                } },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                elevation = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "CALL DEALER",
                    style = TextStyle(
                        color = Color(0xFF3777bc),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}




