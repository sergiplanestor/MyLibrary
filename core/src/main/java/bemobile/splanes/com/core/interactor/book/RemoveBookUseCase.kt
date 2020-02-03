package bemobile.splanes.com.core.interactor.book

import bemobile.splanes.com.core.data.repository.BookRepository
import bemobile.splanes.com.core.domain.Book

class RemoveBookUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(book: Book) = bookRepository.removeBook(book)

}