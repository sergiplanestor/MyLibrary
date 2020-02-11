package bemobile.splanes.com.core.data.repository

import bemobile.splanes.com.core.data.datasource.TagDataSource
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag

class TagRepository(private val tagDataSource: TagDataSource) {

    suspend fun addTag(

        tags: List<Tag>,
        book: Book,
        onSuccess: (isTagAdded: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit

    ) = tagDataSource.add(tags = tags, book = book, onRequestSuccess = onSuccess, onRequestError = onError)

    suspend fun getTags(

        book: Book,
        onSuccess: (tags: List<Tag>) -> Unit,
        onError: (throwable: Throwable) -> Unit

    ) = tagDataSource.fetch(book = book, onRequestSuccess = onSuccess, onRequestError = onError)

    suspend fun removeTag(

        tag: Tag,
        book: Book,
        onSuccess: (isTagRemoved: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit

    ) = tagDataSource.remove(tag = tag, book = book, onRequestSuccess = onSuccess, onRequestError = onError)
}
