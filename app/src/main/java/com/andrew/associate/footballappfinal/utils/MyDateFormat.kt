package com.andrew.associate.footballappfinal.utils

import java.util.*

object MyDateFormat {

    fun initCalendarDate(strDates: String, strTimes: String): Long{
        val dates = strDates.split("-")
        val strYear = dates[0]
        val strMonth = dates[1]
        val strDay = dates[2]

        val times = strTimes.split(":","+")
        val strHour = times[0]
        val strMinute = times[1]

        val year = strYear.toInt()
        val month = strMonth.toInt()
        val day = strDay.toInt()
        val hour = strHour.toInt()
        val minute = strMinute.toInt()

        val calendar = GregorianCalendar(year, month-1, day, hour, minute)

        return calendar.timeInMillis
    }

}