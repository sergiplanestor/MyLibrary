package bemobile.splanes.com.mylibrary.framework


object RestUtils {

    private val const EXPIRATION_DATA = 3600000 // 1h
    
    fun <T> isDataExpirated(items: List<T>, nextUpdate: Long) : Boolean {
        
        if (items.isEmpty) {
            return true
        }
        
        val currentMillis = System.currentTimeMillis()
        
        if (nextUpdate < currentMillis) {
            nextUpdate = currentMillis + EXPIRATION_DATA
            return true
        } else {
            return false
        }        
    }
}
