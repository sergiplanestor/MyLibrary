package bemobile.splanes.com.mylibrary.framework.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RestUtils {

// =================================================================================================
// Constants
// =================================================================================================

    private const val EXPIRATION_DATA: Long = 3600000 // 1h

// =================================================================================================
// Attributes
// =================================================================================================

    private val map: MutableMap<String, Long> = mapOf<String, Long>().toMutableMap()

// =================================================================================================
// Rest Factory
// =================================================================================================

    object RestFactory {

        fun <T> createService(clazz: Class<T>): T {

            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://192.168.1.36:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(clazz)
        }
    }

// =================================================================================================
// Util methods
// =================================================================================================

    fun <T> isDataExpired(restCallName: String, data: T?) : Boolean {
        if (data == null || (data is List<*> && data.isEmpty())) {
            return true
        }
        val currentMillis = System.currentTimeMillis()
        return if (map[restCallName]!! < currentMillis) {
            map[restCallName] = currentMillis + EXPIRATION_DATA
            true
        } else {
            false
        }
    }
}
