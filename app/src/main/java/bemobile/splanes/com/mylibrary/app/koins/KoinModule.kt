package bemobile.splanes.com.mylibrary.app.koins

import bemobile.splanes.com.core.data.datasource.BookDataSource
import bemobile.splanes.com.core.data.datasource.TagDataSource
import bemobile.splanes.com.core.data.datasource.UserDataSource
import bemobile.splanes.com.core.data.repository.BookRepository
import bemobile.splanes.com.core.data.repository.TagRepository
import bemobile.splanes.com.core.data.repository.UserRepository
import bemobile.splanes.com.core.interactor.book.GetBooksUseCase
import bemobile.splanes.com.core.interactor.register.GetStoredUserUseCase
import bemobile.splanes.com.core.interactor.register.RegisterUserUseCase
import bemobile.splanes.com.mylibrary.framework.book.BookService
import bemobile.splanes.com.mylibrary.framework.helper.SharedPreferencesHelper
import bemobile.splanes.com.mylibrary.framework.rest.RestApiDataSource
import bemobile.splanes.com.mylibrary.framework.rest.RestUtils
import bemobile.splanes.com.mylibrary.framework.tag.TagService
import bemobile.splanes.com.mylibrary.framework.user.UserService
import org.koin.dsl.module.module

/* Repositories */
val repositoryModule = module(override = true) {
    factory { BookRepository(get()) }
    factory { TagRepository(get()) }
    factory { UserRepository(get()) }
}

/* DataSources */
val dataSourceModule = module(override = true) {
    factory<BookDataSource> { BookService(get()) }
    factory<TagDataSource> { TagService(get()) }
    factory<UserDataSource> { UserService(get(), get()) }
}

/* UseCases */
val useCaseModule = module(override = true) {
    factory { GetBooksUseCase(get()) }
    factory { GetStoredUserUseCase(get()) }
    factory { RegisterUserUseCase(get()) }
}

/* Rest */
val networkModule = module(override = true) {
    single { RestUtils.RestFactory.createService(RestApiDataSource::class.java) }
}

/* Helpers */
val helperModule = module(override = true) {
    factory { SharedPreferencesHelper(get()) }
}
