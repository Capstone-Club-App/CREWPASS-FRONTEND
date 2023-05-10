package com.example.crewpass_frontend

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

const val BASE_URL = "http://34.64.142.47:8080"

// timeout시간을 설정해줍니다.
private const val CONNECT_TIMEOUT_SEC = 20000L

fun getRetrofit(): Retrofit {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
//        .client(provideOkHttpClient(AppInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}

private fun provideOkHttpClient(
    interceptor: AppInterceptor
): OkHttpClient = OkHttpClient.Builder()
    .run {
        addInterceptor(interceptor)
        build()
    }

class AppInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain)
            : Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("X-Naver-Client-Id", "33chRuAiqlSn5hn8tIme")
            .addHeader("X-Naver-Client-Secret", "fyfwt9PCUN")
            .build()

        proceed(newRequest)
    }
}