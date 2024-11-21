package dev.rulsoft.nb22.presentation.common.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import dev.rulsoft.nb22.BuildConfig
import java.io.File
import java.io.FileOutputStream

fun onResourceReady(context: Context, resource: Bitmap) {

    val cachePath = File(context.cacheDir, "images")
    cachePath.mkdirs() // don't forget to make the directory
    val stream = FileOutputStream(cachePath.toString() + "/image.png") // overwrites this image every time
    resource.compress(Bitmap.CompressFormat.PNG, 100, stream)
    stream.close()

    val imagePath = File(context.cacheDir, "images")
    val newFile = File(imagePath, "image.png")
    val contentUri: Uri = FileProvider.getUriForFile(context, "${BuildConfig.APPLICATION_ID}.provider", newFile)
    Log.d("CartaScreen", "contentUri: $contentUri")
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "image/*"
    intent.putExtra(Intent.EXTRA_STREAM, contentUri)
    context.startActivity(Intent.createChooser(intent, "Choose..."))

}