package com.example.crewpass_frontend

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

const val KR_TIME_DIFF = 9 * 60 * 1000

class  Timestamp_to_SDF {
    fun convert(timestamp: Timestamp) : String{
        var year = timestamp.year
        var month = timestamp.month
        var day = timestamp.day
        var hour = timestamp.hours
        var minute = timestamp.minutes

        var sdf = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분")
        var date = sdf.format(timestamp)

        return date
    }

    fun convert_only_time(timestamp: Timestamp) : String{
        var hour = timestamp.hours
        var minute = timestamp.minutes

        if(hour > 12)
            return "오후 ${hour - 12}:${minute}"
        else
            return "오전 ${hour}:${minute}"
//        var sdf = SimpleDateFormat("HH시 mm분")
//        var time = sdf.format(timestamp)
//
//        return time
    }

    fun timestamp_to_String(timestamp: Long) : String{
        //            2022-01-23T03:34:56.000+00:00
        var sdf = SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss.000+00:00")
//        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        var date = sdf.format(timestamp)

        return date
    }

    fun chatting_timestamp(timestamp: Timestamp) : String{
        var sdf = SimpleDateFormat("yyyy년 MM월 dd일")
        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        var date = sdf.format(timestamp)

        return date
    }
}