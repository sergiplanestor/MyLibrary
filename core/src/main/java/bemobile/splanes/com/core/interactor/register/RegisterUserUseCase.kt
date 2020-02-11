package bemobile.splanes.com.core.interactor.register

import bemobile.splanes.com.core.data.repository.UserRepository
import bemobile.splanes.com.core.domain.User

class RegisterUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(
        
        user: User,
        onSuccess: (isUserRegistered: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit
    
    ) = userRepository.registerUser(user = user, onSuccess = onSuccess, onError = onError)
}
