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
// Enum
// =================================================================================================

    enum class Domain {
        BOOK,
        TAG
    }

// =================================================================================================
// Attributes
// =================================================================================================

    private val map: MutableMap<Domain, Long> = mapOf(
        Pair(Domain.BOOK, 0L),
        Pair(Domain.TAG, 0L)
    ).toMutableMap()

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

    fun <T> isDataExpired(domain: Domain, data: T?) : Boolean {
        if (data == null || (data is List<*> && data.isEmpty())) {
            return true
        }
        val currentMillis = System.currentTimeMillis()
        return if (map[domain]!! < currentMillis) {
            map[domain] = currentMillis + EXPIRATION_DATA
            true
        } else {
            false
        }
    }
}
