package net.elau.example.idempotent_post.web.controller

import net.elau.example.idempotent_post.mapper.toDTO
import net.elau.example.idempotent_post.service.ItemService
import net.elau.example.idempotent_post.web.request.CreateItemRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/items")
class ItemController(private val service: ItemService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestHeader(name = "transaction-token", required = false) transactionToken: UUID? = null,
        @RequestBody createItemRequest: CreateItemRequest
    ) {
        service.create(createItemRequest.toDTO(transactionToken))
    }
}