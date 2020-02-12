package bemobile.splanes.com.mylibrary.framework.service

import bemobile.splanes.com.core.data.datasource.UserDataSource
import bemobile.splanes.com.core.domain.User
import bemobile.splanes.com.mylibrary.framework.helper.SharedPreferencesHelper
import bemobile.splanes.com.mylibrary.framework.rest.RestApiDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserService(
    private val restApiDataSource: RestApiDataSource,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : UserDataSource {
    
    override fun getStoredUser(): User? {

        val id = sharedPreferencesHelper.getInt(SharedPreferencesHelper.PREF_ID)
        val name = sharedPreferencesHelper.getString(SharedPreferencesHelper.PREF_USR)
        val pwd = sharedPreferencesHelper.getString(SharedPreferencesHelper.PREF_PWD)

        return  if (id != null && name != null && pwd != null) {
            User(id, name, pwd)
        } else {
            null
        }
    }

    override fun storeUser(user: User) {
        sharedPreferencesHelper.putInt(SharedPreferencesHelper.PREF_ID, user.id!!)
        sharedPreferencesHelper.putString(SharedPreferencesHelper.PREF_USR, user.name)
        sharedPreferencesHelper.putString(SharedPreferencesHelper.PREF_PWD, user.pwd)
    }

    override suspend fun registerUser(
        user: User,
        onRequestSuccess: (success: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        val disposable = restApiDataSource.register(user = user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { userId: Int? ->
                    if (userId != null) {
                        user.id = userId
                        storeUser(user)
                    }
                    onRequestSuccess(userId != null)
                },
                onRequestError
            )
    }
}
