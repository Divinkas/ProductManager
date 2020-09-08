package com.divinkas.app.productmanager.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    @SuppressWarnings("SimpleDateFormat")
    fun getFullFormatWithMonthName(date: Date, locale: Locale): String {
        val simpleDateTimeFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm", locale)
        return simpleDateTimeFormat.format(date)
    }

    fun isToday(date: Date) = DateUtils.isToday(date.time)
}
