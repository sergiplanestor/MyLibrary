package bemobile.splanes.com.core.interactor.book

import bemobile.splanes.com.core.data.repository.BookRepository
import bemobile.splanes.com.core.domain.Book

class UpdateBookUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(
        
        book: Book,
        onSuccess: (isBookUpdated: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit
    
    ) = bookRepository.updateBook(book = book, onSuccess = onSuccess, onError = onError)
}
