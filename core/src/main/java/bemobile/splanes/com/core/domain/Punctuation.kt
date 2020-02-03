package bemobile.splanes.com.core.domain

import java.io.Serializable

enum class Punctuation(val value: Int) : Serializable {

    STAR_1(1),
    STAR_2(2),
    STAR_3(3),
    STAR_4(4),
    STAR_5(5);

    companion object {
        fun parse(value: Int) : Punctuation? {
            values().forEach { item ->
                if (item.value == value) return item
            }
            return null
        }
    }
}