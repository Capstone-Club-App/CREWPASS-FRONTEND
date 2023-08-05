package com.example.crewpass_frontend.Retrofit.Personal.AI
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class AnalyzeApplicationResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : AnalyzeResult
    )

data class AnalyzeResult(
    @SerializedName("questionCount") val questionCount : Int?,
    @SerializedName("questionAnswerHashMap") val questionAnswerHashMap : HashMap<Int, ArrayList<String>>?,
    @SerializedName("summary") val summary : ArrayList<String>,
    @SerializedName("interview") val interview : ArrayList<String>,
)