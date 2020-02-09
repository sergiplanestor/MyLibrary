package bemobile.splanes.com.core.interactor.register

import bemobile.splanes.com.core.data.repository.UserRepository
import bemobile.splanes.com.core.domain.User

class GetStoredUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke() : User? = userRepository.getStoredUser()
}