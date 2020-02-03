package bemobile.splanes.com.core.data.repository

import bemobile.splanes.com.core.data.datasource.BookDataSource
import bemobile.splanes.com.core.domain.Book

class BookRepository(private val bookDataSource: BookDataSource) {

    suspend fun addBook(book: Book) = bookDataSource.add(book)

    suspend fun getBooks() : List<Book> = bookDataSource.fetchAll()

    suspend fun getBook(bookId: Int) : Book = bookDataSource.fetch(bookId)

    suspend fun removeBook(book: Book) = bookDataSource.remove(book)

    suspend fun updateBook(book: Book) = bookDataSource.update(book)
}