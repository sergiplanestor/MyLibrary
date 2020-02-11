package bemobile.splanes.com.core.data.repository

import bemobile.splanes.com.core.data.datasource.BookDataSource
import bemobile.splanes.com.core.domain.Book

class BookRepository(private val bookDataSource: BookDataSource) {

    suspend fun addBook(

        book: Book,
        onSuccess: (isBookAdded: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit

    ) = bookDataSource.add(book = book, onRequestSuccess = onSuccess, onRequestError = onError)

    suspend fun getBooks(

        onSuccess: (books: List<Book>) -> Unit,
        onError: (throwable: Throwable) -> Unit

    ) = bookDataSource.fetchAll(onRequestSuccess = onSuccess, onRequestError = onError)

    suspend fun getBook(

        bookId: Int,
        onSuccess: (book: Book) -> Unit,
        onError: (throwable: Throwable) -> Unit

    ) = bookDataSource.fetch(id = bookId, onRequestSuccess = onSuccess, onRequestError = onError)

    suspend fun removeBook(

        book: Book,
        onSuccess: (isBookRemoved: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit

    ) = bookDataSource.remove(book = book, onRequestSuccess = onSuccess, onRequestError = onError)

    suspend fun updateBook(

        book: Book,
        onSuccess: (isBookUpdated: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit

    ) = bookDataSource.update(book = book, onRequestSuccess = onSuccess, onRequestError = onError)
}
