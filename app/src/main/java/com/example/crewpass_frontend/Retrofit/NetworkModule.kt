package com.example.crewpass_frontend

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.CookieManager

const val BASE_URL = "http://34.64.142.47:8080"

// timeout시간을 설정해줍니다.
private const val CONNECT_TIMEOUT_SEC = 20000L

fun getRetrofit(): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .cookieJar(JavaNetCookieJar(CookieManager()))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}
//
//object CookieManager {
//    private const val PREFS_NAME = "CookiePrefs"
//    private const val PREFS_COOKIE = "Cookies"
//
//    private val cookiePrefs: SharedPreferences by lazy {
//        MyApp.instance.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//    }
//
//    fun saveCookies(cookies: List<Cookie>) {
//        val cookiesJson = Gson().toJson(cookies)
//        cookiePrefs.edit().putString(PREFS_COOKIE, cookiesJson).apply()
//    }
//
//    fun loadCookies(): List<Cookie> {
//        val cookiesJson = cookiePrefs.getString(PREFS_COOKIE, null)
//        return if (cookiesJson != null) {
//            val type = object : TypeToken<List<Cookie>>() {}.type
//            Gson().fromJson(cookiesJson, type)
//        } else {
//            emptyList()
//        }
//    }
//
//    fun clearCookies() {
//        cookiePrefs.edit().remove(PREFS_COOKIE).apply()
//    }
//}


class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
