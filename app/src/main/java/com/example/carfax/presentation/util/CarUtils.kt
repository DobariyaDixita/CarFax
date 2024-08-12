package com.example.carfax.presentation.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

object CarUtils {


    fun startCall(context: Context, phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        ContextCompat.startActivity(context, callIntent, null)
    }

    fun formatMileage(mileage: Int): String {
        return if (mileage >= 1000) {
            String.format("%.2fk", mileage / 1000.0)
        } else {
            mileage.toString()
        }
    }

    fun handleCallPermission(
        context: Context,
        phoneNumber: String,
        launcher: ActivityResultLauncher<String>
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                launcher.launch(Manifest.permission.CALL_PHONE)
            } else {
                startCall(context, phoneNumber)
            }
        } else {
            startCall(context, phoneNumber)
        }
    }
}
