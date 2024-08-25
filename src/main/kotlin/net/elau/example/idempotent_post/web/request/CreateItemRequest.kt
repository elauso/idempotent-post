package net.elau.example.idempotent_post.web.request

data class CreateItemRequest(
    val name: String,
    val description: String
)
