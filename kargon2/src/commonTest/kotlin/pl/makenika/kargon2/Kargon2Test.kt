package pl.makenika.kargon2

import kotlin.test.Test
import kotlin.test.assertEquals

class Kargon2Test {
    @Test
    fun argon2dHash() {
        val result = Kargon2(
            memory = 19456,
            iterations = 2,
            parallelism = 1,
            outputLength = 32,
            type = Argon2Type.D
        ).hashRaw("my_password", "my_random_salt")
        val hex = result.joinToString("") { it.toUByte().toString(16).padStart(2, '0') }
        assertEquals(
            "e9573d852debf7861b04bfaaad3d657e6d9d8ea5081addd8738137c3dd65e36a",
            hex
        )
    }

    @Test
    fun argon2iHash() {
        val result = Kargon2(
            memory = 19456,
            iterations = 2,
            parallelism = 1,
            outputLength = 32,
            type = Argon2Type.I
        ).hashRaw("my_password", "my_random_salt")
        val hex = result.joinToString("") { it.toUByte().toString(16).padStart(2, '0') }
        assertEquals(
            "a1723a32a22aabaa7d98974d52d57edac08572f0a7c78ea47c81ab831462cbbf",
            hex
        )
    }

    @Test
    fun argon2idHash() {
        val result = Kargon2(
            memory = 19456,
            iterations = 2,
            parallelism = 1,
            outputLength = 32,
            type = Argon2Type.ID
        ).hashRaw("my_password", "my_random_salt")
        val hex = result.joinToString("") { it.toUByte().toString(16).padStart(2, '0') }
        assertEquals(
            "45767b73c39d93b5bf8da6650a78980ef1b02de98c693a328ac42cf00b6a49c2",
            hex
        )
    }
}
