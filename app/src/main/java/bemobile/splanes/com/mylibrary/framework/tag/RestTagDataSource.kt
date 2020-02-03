package bemobile.splanes.com.mylibrary.framework.tag

import bemobile.splanes.com.core.data.datasource.TagDataSource
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag
import bemobile.splanes.com.mylibrary.framework.ApiRepository

class RestTagDataSource(private val apiRepository: ApiRepository) : TagDataSource {

    override suspend fun fetch(book: Book): List<Tag> {
        return apiRepository.fetchTags(book).blockingGet()
    }

    override suspend fun add(tags: List<Tag>, book: Book) {
        apiRepository.addTags(tags, book)
    }

    override suspend fun remove(tag: Tag, book: Book) {
        apiRepository.removeTags(tag, book)
    }
}