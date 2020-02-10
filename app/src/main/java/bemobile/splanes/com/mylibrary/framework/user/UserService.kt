package bemobile.splanes.com.mylibrary.framework.user

import bemobile.splanes.com.core.data.datasource.UserDataSource
import bemobile.splanes.com.core.domain.User
import bemobile.splanes.com.mylibrary.framework.rest.RestApiDataSource

class UserService(private val restApiDataSource: RestApiDataSource) : UserDataSource {

    override fun getStoredUser(): User? {
        return null
    }

    override suspend fun registerUser(user: User) : Boolean {
        return restApiDataSource.register(user).blockingGet()
    }
}