package bemobile.splanes.com.mylibrary.framework.tag

import android.util.SparseArray
import androidx.core.util.getOrDefault
import androidx.core.util.getOrElse
import bemobile.splanes.com.core.data.datasource.TagDataSource
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag
import bemobile.splanes.com.mylibrary.framework.rest.RestApiDataSource
import bemobile.splanes.com.mylibrary.framework.rest.RestUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author sergiplanes on 2020-02-11
 */
class TagService(private val restApiDataSource: RestApiDataSource) : TagDataSource {

// =================================================================================================
// Constants
// =================================================================================================

    private companion object {
        private const val FETCH_ALL = "tag.service.fetch.all"
    }

// =================================================================================================
// Attributes
// =================================================================================================

    private var tagsCache: SparseArray<List<Tag>> = SparseArray()

// =================================================================================================
// TagDataSource Impl
// =================================================================================================

    override suspend fun fetch(
        book: Book,
        onRequestSuccess: (tags: List<Tag>) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {

        if (RestUtils.isDataExpired(FETCH_ALL, tagsCache.getOrDefault(book.id, listOf()))) {

            val disposable = restApiDataSource.fetchTags(book = book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { tags: List<Tag> ->
                        tagsCache.put(book.id, tags)
                        onRequestSuccess(tagsCache[book.id])
                    },
                    onRequestError
                )
        } else {
            onRequestSuccess(tagsCache[book.id])
        }
    }

    override suspend fun add(
        tags: List<Tag>,
        book: Book,
        onRequestSuccess: (isTagAdded: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        val disposable = restApiDataSource.addTags(tags = tags, book = book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success: Boolean ->
                    if (success) {
                        tagsCache.getOrElse(book.id) {

                            tagsCache.put(book.id, listOf())
                            tagsCache[book.id]

                        }.toMutableList().addAll(tags)
                    }
                    onRequestSuccess(success)
                },
                onRequestError
            )
    }

    override suspend fun remove(
        tag: Tag,
        book: Book,
        onRequestSuccess: (isTagRemoved: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        val disposable = restApiDataSource.removeTags(tag = tag, book = book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success: Boolean ->
                    if (success) {
                        tagsCache[book.id].forEachIndexed { index, t -> 
                            if (tag.id == t.id) {
                                tagsCache[book.id].toMutableList().removeAt(index)
                            }
                        }
                    }
                    onRequestSuccess(success)
                },
                onRequestError
            )
    }
}
