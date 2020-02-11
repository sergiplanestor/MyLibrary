package bemobile.splanes.com.core.data.datasource

import bemobile.splanes.com.core.domain.Book

interface BookDataSource {

    suspend fun add(
        book: Book,
        onRequestSuccess: (isBookAdded: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    )

    suspend fun fetchAll(
        onRequestSuccess: (books: List<Book>) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    )

    suspend fun fetch(
        id: Int,
        onRequestSuccess: (book: Book) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    )

    suspend fun remove(
        book: Book,
        onRequestSuccess: (isBookRemoved: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    )

    suspend fun update(
        book: Book,
        onRequestSuccess: (isBookUpdated: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    )
}
