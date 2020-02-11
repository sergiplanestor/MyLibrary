package bemobile.splanes.com.core.interactor.book

import bemobile.splanes.com.core.data.repository.BookRepository
import bemobile.splanes.com.core.domain.Book

class AddBookUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(
        
        book: Book,
        onSuccess: (isBookAdded: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit
    
    ) = bookRepository.addBook(book = book, onSuccess = onSuccess, onError = onError)
}
