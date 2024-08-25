package net.elau.example.idempotent_post.dto

import java.time.Instant
import java.util.*

data class CreateItemDTO(
    val name: String,
    val description: String,
    val requestDate: Instant,
    val transactionToken: UUID?
)
