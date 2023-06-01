package com.example.crewpass_frontend.Data

import java.sql.Timestamp

data class Chat(
//    var chat_id : Int,
    var senderName : String,
    var sendTime : String,
    var content : String,
    var user_id : Int,
    var crew_id : Int,
    var chatRoom_id : Int,
    var viewType : Int
)
