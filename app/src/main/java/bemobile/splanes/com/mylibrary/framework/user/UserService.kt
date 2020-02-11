package bemobile.splanes.com.mylibrary.framework.user

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

        val name = sharedPreferencesHelper.getString(SharedPreferencesHelper.PREF_USR)
        val pwd = sharedPreferencesHelper.getString(SharedPreferencesHelper.PREF_PWD)

        return  if (name != null && pwd != null) {
            User(name, pwd)
        } else {
            null
        }
    }

    override fun storeUser(user: User) {
        sharedPreferencesHelper.putString(SharedPreferencesHelper.PREF_USR, user.name)
        sharedPreferencesHelper.putString(SharedPreferencesHelper.PREF_PWD, user.pwd)
    }

    override suspend fun registerUser(
        user: User,
        onRequestSuccess: (isUserRegistered: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        val disposable = restApiDataSource.register(user = user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success: Boolean ->
                    if (success) {
                        storeUser(user)
                    }
                    onRequestSuccess(success)
                },
                onRequestError
            )
    }
}
