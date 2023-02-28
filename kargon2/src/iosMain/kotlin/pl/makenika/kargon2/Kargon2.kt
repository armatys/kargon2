package pl.makenika.kargon2

import argon2.*
import kotlinx.cinterop.*
import platform.Security.SecRandomCopyBytes
import platform.Security.errSecSuccess
import platform.Security.kSecRandomDefault

public actual class Kargon2 actual constructor(
    private val memory: Int,
    private val iterations: Int,
    private val parallelism: Int,
    private val outputLength: Int,
    private val type: Argon2Type
) {
    public actual fun hashRaw(password: String, salt: String): ByteArray {
        val hash = ByteArray(outputLength)
        val result = hash.usePinned { pinnedHash ->
            val argon2Func = when (type) {
                Argon2Type.D -> ::argon2d_hash_raw
                Argon2Type.I -> ::argon2i_hash_raw
                Argon2Type.ID -> ::argon2id_hash_raw
            }
            argon2Func(
                iterations.convert(),
                memory.convert(),
                parallelism.convert(),
                password.cstr,
                password.length.convert(),
                salt.cstr,
                salt.length.convert(),
                pinnedHash.addressOf(0),
                hash.size.convert()
            )
        }
        check(result == ARGON2_OK) {
            argon2_error_message(result)?.toKString() ?: "Unknown argon2 error code ($result)."
        }
        return hash
    }

    public actual companion object {
        public actual fun randomSalt(length: Int): ByteArray {
            val bytes = ByteArray(length)
            val result = bytes.usePinned { pinnedBytes ->
                SecRandomCopyBytes(kSecRandomDefault, length.convert(), pinnedBytes.addressOf(0))
            }
            check(result == errSecSuccess)
            return bytes
        }
    }
}
