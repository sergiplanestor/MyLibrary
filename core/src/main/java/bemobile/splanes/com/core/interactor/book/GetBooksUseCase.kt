package bemobile.splanes.com.core.interactor.book

import bemobile.splanes.com.core.data.repository.BookRepository
import bemobile.splanes.com.core.domain.Book

class GetBooksUseCase(private val bookRepository: BookRepository) {

    suspend operator fun invoke(

        onSuccess: (books: List<Book>) -> Unit,
        onError: (throwable: Throwable) -> Unit

    ) = bookRepository.getBooks(onSuccess, onError)
}
