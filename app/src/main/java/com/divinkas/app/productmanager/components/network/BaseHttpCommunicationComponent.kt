package com.divinkas.app.productmanager.components.network

import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import com.divinkas.app.productmanager.BuildConfig
import com.divinkas.app.productmanager.config.Configuration
import com.divinkas.app.productmanager.bean.base.BackendError
import com.divinkas.app.productmanager.bean.base.ProductManagerError
import com.divinkas.app.productmanager.bean.base.ProductManagerResult
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BaseHttpCommunicationComponent : HttpCommunicationComponent {
    private val gsonProvider = Gson()

    companion object {
        private const val ACCEPT: String = "application/json"
    }

    private val okHttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String? ->
        Timber.tag("OkHttp: ")
        Timber.d(message)
    }).apply {
        level = if (BuildConfig.DEBUG) BODY else NONE
    }

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ProductManagerHeadersInjector(httpCommunicationComponent = this))
            .addInterceptor(okHttpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Configuration.LINK_USER_API)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonProvider))
            .addCallAdapterFactory(ProductManagerCallAdapter(gsonProvider))
            .build()

    override fun <T> createService(clazz: Class<T>): T = retrofit.create(clazz)

    @Suppress("UNCHECKED_CAST")
    override suspend fun <T> execute(request: suspend () -> Any?): ProductManagerResult<T> {
        val result = runCatching { request() }

        return when {
            result.isSuccess -> ProductManagerResult.Success(result.getOrThrow() as T)
            result.isFailure -> when (val error = result.exceptionOrNull()) {
                is BackendError -> {
                    if (error is BackendError.NotFound)
                        ProductManagerResult.Error(ProductManagerError.Backend())
                    else
                        ProductManagerResult.Error(ProductManagerError.Unknown())
                }
                else -> ProductManagerResult.Error(ProductManagerError.Unknown())
            }
            else -> ProductManagerResult.Error(ProductManagerError.Unknown())
        }
    }

    override fun getAcceptHeader(): String = ACCEPT
}