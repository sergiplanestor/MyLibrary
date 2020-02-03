package bemobile.splanes.com.core.domain

import java.io.Serializable

data class Book(

    var id: Int,
    var title: String,
    var author: String?,
    var imageMain: String?,
    var imageSecondary: String?,
    var editorial: String?,
    var synopsis: String?,
    var genre: Genre?,
    var tags: List<Tag>?,
    var priority: ReadPriority?,
    var alreadyRead: Boolean,
    var alreadyBought: Boolean,
    var punctuation: Punctuation?,
    var price: Double?,
    var publicationDate: Long?,
    var creationDate: Long?,
    var updateDate: Long?

) : Serializable