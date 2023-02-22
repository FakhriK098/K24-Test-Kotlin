package id.fakhri_khairi.core.misc

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun format(date: Date, format: String, locale: Locale = Locale("in", "id")): String {
        val sdf = SimpleDateFormat(format, locale)

        return sdf.format(date)
    }
}