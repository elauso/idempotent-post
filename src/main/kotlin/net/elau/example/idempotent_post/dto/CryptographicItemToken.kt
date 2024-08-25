package net.elau.example.idempotent_post.dto

import kotlinx.serialization.Serializable

@Serializable
data class CryptographicItemToken(
    val name: String,
    val description: String,
    val timestamp: String
)
