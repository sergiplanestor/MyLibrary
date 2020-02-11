package bemobile.splanes.com.core.interactor.book

import bemobile.splanes.com.core.data.repository.BookRepository
import bemobile.splanes.com.core.domain.Book

class GetBookUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(
        
        bookId: Int,
        onSuccess: (books: Book) -> Unit,
        onError: (throwable: Throwable) -> Unit
    
    ) = bookRepository.getBook(bookId = bookId, onSuccess = onSuccess, onError = onError)
}
