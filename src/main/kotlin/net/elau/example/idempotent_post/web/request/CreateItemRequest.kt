package net.elau.example.idempotent_post.web.request

import jakarta.validation.constraints.PastOrPresent
import java.time.Instant

data class CreateItemRequest(
    val name: String,
    val description: String,
    @PastOrPresent
    val requestDate: Instant
)
