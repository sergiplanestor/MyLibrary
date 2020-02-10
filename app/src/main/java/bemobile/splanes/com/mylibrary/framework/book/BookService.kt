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

    override suspend fun add(book: Book, callback: (success: Boolean) -> Unit) {

        val disposable= restApiDataSource.addBook(book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    if (success) {
                        bookCache.add(book)
                    }
                    callback(success)
                },
                RestUtils.errorConsumer()
            )
    }
    
    override suspend fun fetchAll(callback: (books: List<Book>) -> Unit) {

        if (RestUtils.isDataExpired(domain, bookCache)) {

            val disposable = restApiDataSource.fetchBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { books ->
                        bookCache.clear()
                        bookCache.addAll(books)
                    },
                    RestUtils.errorConsumer()
                )
        } else {
            callback(bookCache)
        }
    }
    
    override suspend fun fetch(id: Int, callback: (book: Book) -> Unit) {
    }
    
    override suspend fun remove(book: Book, callback: (success: Boolean) -> Unit) {
    }
    
    override suspend fun update(book: Book, callback: (success: Boolean) -> Unit) {
    }
}
