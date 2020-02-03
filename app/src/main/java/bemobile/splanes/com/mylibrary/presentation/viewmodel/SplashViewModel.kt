package bemobile.splanes.com.mylibrary.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.interactor.book.GetBooksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SplashViewModel : ViewModel(), KoinComponent {

    private val mUseCase : GetBooksUseCase by inject()

    fun getBooks() : LiveData<List<Book>> {

        val liveData = MutableLiveData<List<Book>>()
        runBlocking(Dispatchers.IO) {
            val books = mUseCase.invoke()
            liveData.postValue(books)
        }

        return liveData
    }

}