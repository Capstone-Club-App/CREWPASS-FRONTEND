package com.example.crewpass_frontend.Retrofit.Cookie

import android.content.Context
import android.content.SharedPreferences
import com.example.crewpass_frontend.MyApp
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

//class AddCookiesInterceptor : Interceptor {
//
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val PREFS_NAME = "CookiePrefs"
//        val builder : Request.Builder = chain.request().newBuilder()
//        var auth = ""
//
//        builder.addHeader("Auth", auth)
//        builder.addHeader("User-Agent", "Dev-Geek-Sample-App")
//        // Preference에서 cookies 가져오기
////        val preferences = MyApp.instance.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        val preferences : Set<String> = MyApp.instance.getSharedPreference(PREFS_NAME, HashSet<String>())
//    }
//
//}
