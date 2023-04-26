package com.example.crewpass_frontend.Data

import java.sql.Timestamp

data class Chat(
    var chat_id : Int,
    var chatRoom_id : Int,
    var user_id : Int,
    var created_at : Timestamp,
    var content : String,
    var writer : String,
    var viewType : Int
)
