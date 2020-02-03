package bemobile.splanes.com.mylibrary.app.koins

import bemobile.splanes.com.core.data.datasource.BookDataSource
import bemobile.splanes.com.core.data.datasource.TagDataSource
import bemobile.splanes.com.core.data.repository.BookRepository
import bemobile.splanes.com.core.data.repository.TagRepository
import bemobile.splanes.com.core.interactor.book.GetBooksUseCase
import bemobile.splanes.com.mylibrary.framework.RestAdapter
import bemobile.splanes.com.mylibrary.framework.ApiRepository
import bemobile.splanes.com.mylibrary.framework.book.RestBookDataSource
import bemobile.splanes.com.mylibrary.framework.tag.RestTagDataSource
import org.koin.dsl.module.module

val appModule = module(override = true) {


}

val networkModule = module(override = true) {
    factory { BookRepository(get()) }
    factory { TagRepository(get()) }
    factory<BookDataSource> { RestBookDataSource(get()) }
    factory<TagDataSource> { RestTagDataSource(get()) }
    factory { GetBooksUseCase(get()) }
    single { RestAdapter.createService(ApiRepository::class.java) }
}