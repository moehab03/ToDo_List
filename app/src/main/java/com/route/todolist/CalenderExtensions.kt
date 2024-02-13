package com.route.todolist

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

fun CalendarDay.timeInMillis(): Long {
    val calendar = Calendar.getInstance()
    calendar.set(this.year, this.month - 1, this.day)
    calendar.clearTime()
    return calendar.timeInMillis
}

fun Calendar.clearTime() {
    this.clear(Calendar.HOUR)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MILLISECOND)
}