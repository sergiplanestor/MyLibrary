package bemobile.splanes.com.core.data.datasource

import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag

interface TagDataSource {

    suspend fun fetch(book: Book) : List<Tag>

    suspend fun add(tags: List<Tag>, book: Book)

    suspend fun remove(tag: Tag, book: Book)
}