package bemobile.splanes.com.core.interactor.register

import bemobile.splanes.com.core.data.repository.UserRepository
import bemobile.splanes.com.core.domain.User

/**
 * @author sergiplanes on 2020-02-11
 */
class StoreUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke(user: User) = userRepository.storeUser(user)
}
