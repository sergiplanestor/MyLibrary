package bemobile.splanes.com.core.data.datasource

import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag

interface TagDataSource {

    suspend fun fetch(
        book: Book,
        onRequestSuccess: (tags: List<Tag>) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    )

    suspend fun add(
        tags: List<Tag>,
        book: Book,
        onRequestSuccess: (isTagAdded: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    )

    suspend fun remove(
        tag: Tag,
        book: Book,
        onRequestSuccess: (isTagRemoved: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    )
}
