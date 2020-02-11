package bemobile.splanes.com.mylibrary.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bemobile.splanes.com.core.domain.User
import bemobile.splanes.com.core.interactor.register.RegisterUserUseCase
import bemobile.splanes.com.mylibrary.R
import bemobile.splanes.com.mylibrary.presentation.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class RegisterViewModel : BaseViewModel(), KoinComponent {

    private val mRegisterUserUseCase: RegisterUserUseCase by inject()

    data class FieldState(val status: Boolean,
                          val errorField: String?,
                          val errorMessage: Int?)

    fun getFieldsState(userName: String?, pwd: String?, pwd2: String?) : FieldState {

        // TODO: this method should return LiveData and call service to determine if username is valid (UNIQUE)

        var status = true
        var errorField: String? = null
        var errorMessage: Int? = null

        if (userName.isNullOrEmpty() || userName.isBlank()) {

            status = false
            errorField = "username"
            errorMessage = R.string.error_empty_field

        } else if (pwd.isNullOrEmpty() || pwd.isBlank()) {

            status = false
            errorField = "pwd"
            errorMessage = R.string.error_empty_field

        } else if (pwd.length < 8) {

            status = false
            errorField = "pwd"
            errorMessage = R.string.error_pwd_invalid

        } else if (pwd2.isNullOrEmpty() || pwd2.isBlank()) {

            status = false
            errorField = "pwd2"
            errorMessage = R.string.error_empty_field

        } else if (pwd != pwd2) {

            status = false
            errorField = "pwds"
            errorMessage = R.string.error_pwd_not_match

        }

        return FieldState(status, errorField, errorMessage)
    }

    fun registerUser(user: User) : LiveData<Boolean> {
        
        val liveData = MutableLiveData<Boolean>()
        
        runBlocking(Dispatchers.IO) {
            mRegisterUserUseCase.invoke(
                user = user,
                onSuccess = {
                    liveData.postValue(it)
                },
                onError = errorConsumer()
            )
        }
        
        return liveData
    }
}
