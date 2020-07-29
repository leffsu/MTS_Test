package su.leff.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val authInterceptor = Interceptor { chain ->
    val newUrl = chain.request().url()
        .newBuilder()
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}

const val baseUrl = "https://raw.githubusercontent.com/ar2code/apitest/master/"

fun retrofit(): Retrofit = Retrofit.Builder()
    .client(posterClient)
    .baseUrl(baseUrl)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

private val posterClient = OkHttpClient().newBuilder()
    .addInterceptor(authInterceptor)
    .build()

val api: Api = retrofit().create(Api::class.java)