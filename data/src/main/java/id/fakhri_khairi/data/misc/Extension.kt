package id.fakhri_khairi.data.misc

import android.os.Build
import java.security.MessageDigest
import java.util.Base64

fun String.convertToHash() : String {
    val md = MessageDigest.getInstance("SHA-256")
    val input = this.toByteArray()
    val bytes= md.digest(input)
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Base64.getEncoder().encodeToString(bytes)
    } else {
        this
    }
}