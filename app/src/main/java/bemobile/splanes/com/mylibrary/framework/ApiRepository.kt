package bemobile.splanes.com.mylibrary.framework

import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag
import bemobile.splanes.com.core.domain.User
import io.reactivex.Single
import retrofit2.http.*

interface ApiRepository {

    @POST("/rest/api/login")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun doLogin() : Single<String>

    @POST("/rest/api/register")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun register(@Body user: User) : Single<Boolean>

    @GET("/rest/api/books")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun fetchBooks() : Single<List<Book>>

    @GET("/rest/api/book")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun fetchBook(@Query("id") bookId: Int) : Single<Book>

    @POST("/rest/api/book/add")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun addBook(@Body book: Book)

    @POST("/rest/api/book/update")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun updateBook(@Body book: Book)

    @POST("/rest/api/book/remove")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun removeBook(@Body book: Book)

    @GET("/tags")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun fetchTags(@Query("book") book: Book) : Single<List<Tag>>

    @POST("/tags/add")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun addTags(@Body tags: List<Tag>, @Body book: Book)

    @POST("/tags/remove")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun removeTags(@Body tag: Tag, @Body book: Book)
}