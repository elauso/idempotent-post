package net.elau.example.idempotent_post.dto

import java.util.*

data class CreateItemDTO(
    val name: String,
    val description: String,
    val transactionToken: UUID?
)
