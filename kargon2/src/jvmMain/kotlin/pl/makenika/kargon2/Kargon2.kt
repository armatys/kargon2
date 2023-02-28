package pl.makenika.kargon2

import com.password4j.Argon2Function
import com.password4j.Password
import com.password4j.SaltGenerator
import com.password4j.types.Argon2

public actual class Kargon2 actual constructor(
    memory: Int,
    iterations: Int,
    parallelism: Int,
    outputLength: Int,
    type: Argon2Type
) {
    private val argon2Function = Argon2Function.getInstance(
        memory,
        iterations,
        parallelism,
        outputLength,
        type.toPassword4jType()
    )

    public actual fun hashRaw(password: String, salt: String): ByteArray {
        return Password.hash(password)
            .addSalt(salt)
            .with(argon2Function)
            .bytes
    }

    private fun Argon2Type.toPassword4jType(): Argon2 {
        return when (this) {
            Argon2Type.D -> Argon2.D
            Argon2Type.I -> Argon2.I
            Argon2Type.ID -> Argon2.ID
        }
    }

    public actual companion object {
        public actual fun randomSalt(length: Int): ByteArray {
            return SaltGenerator.generate(length)
        }
    }
}
