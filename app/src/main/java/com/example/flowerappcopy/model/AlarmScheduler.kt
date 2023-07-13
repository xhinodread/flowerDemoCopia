package com.example.flowerappcopy.model

import com.example.flowerappcopy.data.AlarmItem

interface AlarmScheduler {
    fun schedule(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)
}