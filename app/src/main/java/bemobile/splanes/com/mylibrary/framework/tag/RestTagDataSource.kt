package bemobile.splanes.com.mylibrary.framework.tag

import bemobile.splanes.com.core.data.datasource.TagDataSource
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag
import bemobile.splanes.com.mylibrary.framework.rest.RestApiDataSource

class RestTagDataSource(private val restApiDataSource: RestApiDataSource) : TagDataSource {

    override suspend fun fetch(book: Book): List<Tag> {
        return restApiDataSource.fetchTags(book).blockingGet()
    }

    override suspend fun add(tags: List<Tag>, book: Book) {
        restApiDataSource.addTags(tags, book)
    }

    override suspend fun remove(tag: Tag, book: Book) {
        restApiDataSource.removeTags(tag, book)
    }
}