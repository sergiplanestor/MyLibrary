package bemobile.splanes.com.mylibrary.framework.user

import bemobile.splanes.com.core.data.datasource.UserDataSource
import bemobile.splanes.com.core.domain.User
import bemobile.splanes.com.mylibrary.framework.ApiRepository

class UserService(private val apiRepository: ApiRepository) : UserDataSource {

    override fun getStoredUser(): User? {
        return null
    }

    override suspend fun registerUser(user: User) : Boolean {
        return apiRepository.register(user).blockingGet()
    }
}