package net.elau.example.idempotent_post.mapper

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.elau.example.idempotent_post.dto.CreateItemDTO
import net.elau.example.idempotent_post.dto.CryptographicItemToken
import net.elau.example.idempotent_post.model.Item
import net.elau.example.idempotent_post.util.getBytesFromUUID
import net.elau.example.idempotent_post.web.request.CreateItemRequest
import org.springframework.util.DigestUtils.md5DigestAsHex
import java.util.*

fun CreateItemRequest.toDTO(transactionToken: UUID?): CreateItemDTO {
    return CreateItemDTO(
        name = this.name,
        description = this.description,
        requestDate = this.requestDate,
        transactionToken = transactionToken
    )
}

fun CreateItemDTO.toModel(): Item {
    return Item(
        name = this.name,
        description = this.description,
        requestDate = this.requestDate,
        transactionToken = getTransactionToken(this)
    )
}

fun CreateItemDTO.toCrypto(): CryptographicItemToken {
    return CryptographicItemToken(
        name = this.name,
        description = this.description,
        timestamp = this.requestDate.toString()
    )
}

private fun getTransactionToken(createItemDTO: CreateItemDTO): String =
    createItemDTO.transactionToken?.run { md5DigestAsHex(getBytesFromUUID(this)) }
        ?: md5DigestAsHex(Json.encodeToString(createItemDTO.toCrypto()).toByteArray())
