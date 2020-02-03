package bemobile.splanes.com.core.domain

import java.io.Serializable

enum class ReadPriority(val value: String) : Serializable {

    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    companion object {
        fun parse(value: String) : ReadPriority? {
            values().forEach { item ->
                if (item.value == value) return item
            }
            return null
        }
    }
}