package bemobile.splanes.com.core.interactor.book

import bemobile.splanes.com.core.data.repository.BookRepository
import bemobile.splanes.com.core.domain.Book

class RemoveBookUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(
        
        book: Book,
        onSuccess: (isBookRemoved: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit
    
    ) = bookRepository.removeBook(book = book, onSuccess = onSuccess, onError = onError)
}
