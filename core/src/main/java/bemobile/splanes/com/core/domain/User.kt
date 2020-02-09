package bemobile.splanes.com.core.domain

import java.io.Serializable

data class User(
    var name: String,
    var pwd: String
) : Serializable