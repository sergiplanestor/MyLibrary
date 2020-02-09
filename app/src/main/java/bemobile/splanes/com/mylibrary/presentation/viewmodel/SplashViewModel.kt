package bemobile.splanes.com.mylibrary.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.User
import bemobile.splanes.com.core.interactor.book.GetBooksUseCase
import bemobile.splanes.com.core.interactor.register.GetStoredUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SplashViewModel : ViewModel(), KoinComponent {

    private val mGetBooksUseCase : GetBooksUseCase by inject()

    private val mGetStoredUserUseCase : GetStoredUserUseCase by inject()


    fun getStoredUser() : LiveData<User?> {
        val liveData = MutableLiveData<User?>()

        val user = mGetStoredUserUseCase.invoke()
        liveData.postValue(user)

        return liveData
    }


    fun getBooks() : LiveData<List<Book>> {

        val liveData = MutableLiveData<List<Book>>()
        runBlocking(Dispatchers.IO) {
            /*val books = mUseCase.invoke()
            liveData.postValue(books)*/
        }

        return liveData
    }

}