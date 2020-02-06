package bemobile.splanes.com.core.domain.states

data class RegisterState(
    val state: State = State.UNREGISTERED
) {

    enum class State {
        REGISTERED_AUTO,
        UNREGISTERED,
        // TODO: REGISTERED_MANUAL
    }

}