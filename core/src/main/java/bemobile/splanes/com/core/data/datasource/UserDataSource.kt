package bemobile.splanes.com.core.data.datasource

import bemobile.splanes.com.core.domain.User

interface UserDataSource {

    fun getStoredUser() : User?

    suspend fun registerUser(user: User) : Boolean
}