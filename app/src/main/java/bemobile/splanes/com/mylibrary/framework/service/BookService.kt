package bemobile.splanes.com.mylibrary.framework.service

import bemobile.splanes.com.core.data.datasource.BookDataSource
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.mylibrary.framework.rest.RestApiDataSource
import bemobile.splanes.com.mylibrary.framework.rest.RestUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookService(private val restApiDataSource: RestApiDataSource) : BookDataSource {

// =================================================================================================
// Constants
// =================================================================================================
    
    private companion object {
        private const val FETCH_ALL = "book.service.fetch.all"
        private const val FETCH_ONE = "book.service.fetch.one"
    }
    
// =================================================================================================
// Attributes
// =================================================================================================
    
    private var booksCache: MutableList<Book> = listOf<Book>().toMutableList()
    private var bookCache: Book? = null

// =================================================================================================
// BookDataSource Impl
// =================================================================================================

    override suspend fun add(
        book: Book,
        onRequestSuccess: (isBookAdded: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        val disposable = restApiDataSource.addBook(book = book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success: Boolean ->
                    if (success) {
                        booksCache.add(book)
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
        if (RestUtils.isDataExpired(FETCH_ALL, booksCache)) {

            val disposable = restApiDataSource.fetchBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { books: List<Book> ->
                        booksCache.clear()
                        booksCache.addAll(books)
                        onRequestSuccess(booksCache)
                    },
                    onRequestError
                )
        } else {
            onRequestSuccess(booksCache)
        }
    }

    override suspend fun fetch(
        id: Int,
        onRequestSuccess: (book: Book) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        if (RestUtils.isDataExpired(FETCH_ONE, bookCache)) {

            val disposable = restApiDataSource.fetchBook(bookId = id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { book: Book ->
                        bookCache = book
                        onRequestSuccess(bookCache!!)
                    },
                    onRequestError
                )
        } else {
            onRequestSuccess(bookCache!!)
        }
    }

    override suspend fun remove(
        book: Book,
        onRequestSuccess: (isBookRemoved: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        val disposable = restApiDataSource.removeBook(book = book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success: Boolean ->
                    if (success) {
                        booksCache.forEachIndexed { index, item ->
                            if (item.id == book.id) {
                                booksCache.removeAt(index)
                            }
                        }
                        if (bookCache?.id == book.id) {
                            bookCache = null
                        }
                    }
                    onRequestSuccess(success)
                },
                onRequestError
            )
    }

    override suspend fun update(
        book: Book,
        onRequestSuccess: (isBookUpdated: Boolean) -> Unit,
        onRequestError: (throwable: Throwable) -> Unit
    ) {
        val disposable = restApiDataSource.updateBook(book = book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success: Boolean ->
                    if (success) {
                        booksCache.forEachIndexed { index, item ->
                            if (item.id == book.id) {
                                booksCache[index] = book
                            }
                        }
                        if (bookCache?.id == book.id) {
                            bookCache = book
                        }
                    }
                    onRequestSuccess(success)
                },
                onRequestError
            )
    }
}
