package bemobile.splanes.com.core.data.datasource

import bemobile.splanes.com.core.domain.User

interface UserDataSource {

    fun getStoredUser() : User?

    fun storeUser(user: User)

    suspend fun registerUser(
        user: User,
        onRequestSuccess: (success: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    )
}
