package org.rulsoft.ap.nb22.presentation.carta.holder

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import org.rulsoft.ap.nb22.presentation.common.utils.onResourceReady
import dev.shreyaspatil.capturable.controller.CaptureController
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import org.rulsoft.ap.nb22.presentation.BuildConfig

class CapturerStateHolder(
    val captureController: CaptureController
) {
    @OptIn(ExperimentalComposeApi::class)
    @Composable
    fun OnSendCapture() {
        val context = LocalContext.current
        LaunchedEffect(captureController) {
            val bitmapAsync = captureController.captureAsync(Bitmap.Config.ARGB_8888)
            try {
                val bitmap = bitmapAsync.await()
                // This is captured bitmap of a content inside Capturable Composable.
                Log.d("NB22AppState", "bitmap: $bitmap")
                //Bitmap is captured successfully. Do something with it!
                onResourceReady(context, bitmap.asAndroidBitmap(), BuildConfig.APPLICATION_ID)
            } catch (error: Throwable) {
                Log.e("NB22AppState", "error: $error")
                // Error occurred. Handle it!
            }
        }
    }
}

@Composable
fun rememberCapturerStateHolder(
    captureController: CaptureController = rememberCaptureController()
): CapturerStateHolder {
    return remember(captureController) {
        CapturerStateHolder(captureController)
    }
}