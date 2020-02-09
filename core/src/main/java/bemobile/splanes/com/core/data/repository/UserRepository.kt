package bemobile.splanes.com.core.data.repository

import bemobile.splanes.com.core.data.datasource.UserDataSource
import bemobile.splanes.com.core.domain.User

class UserRepository(private val userDataSource: UserDataSource) {

    fun getStoredUser() : User? = userDataSource.getStoredUser()

    suspend fun registerUser(user: User) = userDataSource.registerUser(user)
}