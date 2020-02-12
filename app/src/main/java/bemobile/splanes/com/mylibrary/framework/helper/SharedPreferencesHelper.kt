package bemobile.splanes.com.mylibrary.framework.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

// =================================================================================================
// Keys
// =================================================================================================

    companion object Key {
        private const val SHARED_NAME: String = "my_library.shared.prefs"
        const val PREF_ID: String = "my_library.shared.prefs._id"
        const val PREF_USR: String = "my_library.shared.prefs.usr"
        const val PREF_PWD: String = "my_library.shared.prefs.pwd"
    }

// =================================================================================================
// Attributes
// =================================================================================================

    private val mSharedPrefs: SharedPreferences

// =================================================================================================
// Init block
// =================================================================================================

    init {
        mSharedPrefs = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    }

// =================================================================================================
// Helper methods
// =================================================================================================

    fun putString(key: String, value: String) = mSharedPrefs.edit().run { putString(key, value) }.apply()

    fun getString(key: String) : String? = mSharedPrefs.getString(key, null)

    fun putInt(key: String, value: Int) = mSharedPrefs.edit().run { putInt(key, value) }.apply()

    fun getInt(key: String) : Int? {
        val value = mSharedPrefs.getInt(key, -1)
        return if (value == -1) null else value
    }
}
