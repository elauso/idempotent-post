package net.elau.example.idempotent_post.dto

import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class CryptographicItemToken(
    val name: String,
    val description: String,
    val timestamp: String
)
