package bemobile.splanes.com.core.data.repository

import bemobile.splanes.com.core.data.datasource.TagDataSource
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag

class TagRepository(private val tagDataSource: TagDataSource) {

    suspend fun addTag(tags: List<Tag>, book: Book) = tagDataSource.add(tags, book)

    suspend fun getTags(book: Book) : List<Tag> = tagDataSource.fetch(book)

    suspend fun removeTag(tag: Tag, book: Book) = tagDataSource.remove(tag, book)
}