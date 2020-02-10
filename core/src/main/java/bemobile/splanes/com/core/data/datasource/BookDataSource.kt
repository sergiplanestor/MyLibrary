package bemobile.splanes.com.core.data.datasource

import bemobile.splanes.com.core.domain.Book

interface BookDataSource {

    suspend fun add(book: Book, callback: (success: Boolean) -> Unit)

    suspend fun fetchAll(callback: (books: List<Book>) -> Unit)

    suspend fun fetch(id: Int, callback: (book: Book) -> Unit)

    suspend fun remove(book: Book, callback: (success: Boolean) -> Unit)

    suspend fun update(book: Book, callback: (success: Boolean) -> Unit)
}