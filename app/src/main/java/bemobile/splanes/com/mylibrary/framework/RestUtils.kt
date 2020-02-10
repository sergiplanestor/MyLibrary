package bemobile.splanes.com.mylibrary.framework


object RestUtils {

    fun <T> isDataExpirated(items: List<T>, nextUpdate: Long) : Boolean {
        
        if (items.isEmpty) {
            return true
        }
        
        val currentMillis = System.currentTimeMillis()
        
        if (nextUpdate < currentMillis) {
            nextUpdate = currentMillis + BookService.EXPIRATION_DATA
            return true
        } else {
            return false
        }        
    }
}
