package cicadas.mixtape.bettermacros.event

enum class Action {
    RELEASE,
    PRESS,
    UNKNOWN;

    companion object {
        fun get(code: Int) = when(code) {
            0 -> RELEASE
            1 -> PRESS
            else -> UNKNOWN
        }
    }
}