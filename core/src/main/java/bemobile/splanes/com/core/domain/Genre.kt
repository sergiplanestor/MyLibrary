package bemobile.splanes.com.core.domain

import java.io.Serializable

enum class Genre(val value: String) : Serializable {

    HORROR("Horror"),
    ROMANTIC("Romantic"),
    FANTASY("Fantasy"),
    DRAMA("Drama"),
    THRILLER("Thriller"),
    BIBLIOGRAPHY("Bibliography"),
    HISTORIC("Historic"),
    SCIFY("Scify"),
    STORY("Story"),
    HELPFUL("Helpful"),
    PHILOSOPHY("Philosophy"),
    CLASSIC("Classic"),
    TRAVEL("Travel"),
    POLITICAL("Political"),
    SOCIALISM("Socialism"),
    COMIC("Comic"),
    TECHNICAL("Technical"),
    SCIENCE("Science");

    companion object {
        fun parse(value: String) : Genre? {
            values().forEach { item ->
                if (item.value == value) return item
            }
            return null
        }
    }

}