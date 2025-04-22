package com.example.appsrp

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

// Conversión de tipos a RequestBody
fun String.toRequestBody(): RequestBody =
    this.toRequestBody("text/plain".toMediaType())

// Conversión de Uri a MultipartBody.Part
fun Uri.toPart(partName: String): MultipartBody.Part? {
    return try {
        val file = File(this.path)
        MultipartBody.Part.createFormData(
            name = partName,
            filename = file.name,
            body = file.asRequestBody("image/*".toMediaType())
        )
    } catch (e: Exception) {
        null
    }
}

fun Context.getMultipartFromUri(name: String, uri: Uri): MultipartBody.Part? {
    val contentResolver = contentResolver
    val inputStream = contentResolver.openInputStream(uri) ?: return null
    val fileName = getFileName(uri)
    val requestBody = inputStream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(name, fileName, requestBody)
}

fun Context.getFileName(uri: Uri): String {
    var result: String? = null
    if (uri.scheme == "content") {
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor.use {
            if (it != null && it.moveToFirst()) {
                result = it.getString(it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
            }
        }
    }
    if (result == null) {
        result = uri.path
        val cut = result?.lastIndexOf('/')
        if (cut != -1) {
            result = result?.substring(cut!! + 1)
        }
    }
    return result ?: "image.jpg"
}