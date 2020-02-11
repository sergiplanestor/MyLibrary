package bemobile.splanes.com.mylibrary.framework.book

import bemobile.splanes.com.core.data.datasource.BookDataSource
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.mylibrary.framework.rest.RestApiDataSource
import bemobile.splanes.com.mylibrary.framework.rest.RestUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookService(private val restApiDataSource: RestApiDataSource) : BookDataSource {

// =================================================================================================
// Attributes
// =================================================================================================

    private var bookCache: MutableList<Book> = listOf<Book>().toMutableList()
    private val domain: RestUtils.Domain = RestUtils.Domain.BOOK

// =================================================================================================
// BookDataSource Impl
// =================================================================================================

    override suspend fun add(
        book: Book,
        onRequestSuccess: (isBookAdded: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {

        val disposable = restApiDataSource.addBook(book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    if (success) {
                        bookCache.add(book)
                    }
                    onRequestSuccess(success)
                },
                onRequestError
            )
        
    }

    override suspend fun fetchAll(
        onRequestSuccess: (books: List<Book>) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        if (RestUtils.isDataExpired(domain, bookCache)) {

            val disposable = restApiDataSource.fetchBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { books ->
                        bookCache.clear()
                        bookCache.addAll(books)
                        onRequestSuccess(bookCache)
                    },
                    onRequestError
                )
        } else {
            onRequestSuccess(bookCache)
        }
    }

    override suspend fun fetch(
        id: Int,
        onRequestSuccess: (book: Book) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        
    }

    override suspend fun remove(
        book: Book,
        onRequestSuccess: (isBookRemoved: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        
    }

    override suspend fun update(
        book: Book,
        onRequestSuccess: (isBookUpdated: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        
    }
}
