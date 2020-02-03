package bemobile.splanes.com.mylibrary.framework.book

import bemobile.splanes.com.core.data.datasource.BookDataSource
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.mylibrary.framework.ApiRepository


class RestBookDataSource(private val apiRepository: ApiRepository) : BookDataSource {

    override suspend fun add(book: Book) {
        apiRepository.addBook(book)
    }

    override suspend fun fetchAll(): List<Book> {
        return apiRepository.fetchBooks().blockingGet()
    }

    override suspend fun fetch(id: Int): Book {
        return apiRepository.fetchBook(id).blockingGet()
    }

    override suspend fun remove(book: Book) {
        apiRepository.removeBook(book)
    }

    override suspend fun update(book: Book) {
        apiRepository.updateBook(book)
    }
}