package bemobile.splanes.com.core.data.datasource

import bemobile.splanes.com.core.domain.Book

interface BookDataSource {

    suspend fun add(book: Book)

    suspend fun fetchAll() : List<Book>

    suspend fun fetch(id: Int): Book

    suspend fun remove(book: Book)

    suspend fun update(book: Book)
}