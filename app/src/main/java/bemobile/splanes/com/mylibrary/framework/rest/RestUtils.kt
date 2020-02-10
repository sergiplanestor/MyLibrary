package bemobile.splanes.com.mylibrary.framework.rest


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
// Util methods
// =================================================================================================

    fun <T> isDataExpired(domain: Domain, items: List<T>) : Boolean {
        if (items.isEmpty()) {
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

    fun errorConsumer() : (error: Throwable) -> Unit {
        return {
            // FIXME: Show pop up or something like that?
            it.printStackTrace()
        }
    }
}
