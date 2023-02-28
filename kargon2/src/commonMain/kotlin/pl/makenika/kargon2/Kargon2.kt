package pl.makenika.kargon2

public expect class Kargon2 constructor(
    memory: Int,
    iterations: Int,
    parallelism: Int,
    outputLength: Int,
    type: Argon2Type
) {
    public fun hashRaw(password: String, salt: String): ByteArray

    public companion object {
        public fun randomSalt(length: Int): ByteArray
    }
}

public enum class Argon2Type {
    D, I, ID
}
