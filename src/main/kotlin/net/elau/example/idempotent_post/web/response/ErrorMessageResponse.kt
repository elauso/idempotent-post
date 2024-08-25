package net.elau.example.idempotent_post.web.response

import java.time.Instant

data class ErrorMessageResponse(
    val statusCode: Int,
    val timestamp: Instant,
    val message: String?,
    val description: String
)
