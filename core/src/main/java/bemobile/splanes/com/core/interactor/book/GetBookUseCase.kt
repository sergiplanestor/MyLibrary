package bemobile.splanes.com.core.interactor.book

import bemobile.splanes.com.core.data.repository.BookRepository
import bemobile.splanes.com.core.domain.Book

class GetBookUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(bookId: Int) : Book = bookRepository.getBook(bookId)
}