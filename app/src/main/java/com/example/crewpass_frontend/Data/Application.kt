package com.example.crewpass_frontend.Data

import java.sql.Timestamp

data class Application (
    var application_id : Int,
    var submit_time : Timestamp,
    var answer1 : String,
    var answer2 : String,
    var answer3 : String,
    var answer4 : String,
    var answer5 : String,
    var answer6 : String,
    var answer7 : String,
    var answer1_count : Int,
    var answer2_count : Int,
    var answer3_count : Int,
    var answer4_count : Int,
    var answer5_count : Int,
    var answer6_count : Int,
    var answer7_count : Int,
    var question_id : Int,
    var user_id : Int
    )