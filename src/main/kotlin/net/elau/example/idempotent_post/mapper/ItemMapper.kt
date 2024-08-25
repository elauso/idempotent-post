package net.elau.example.idempotent_post.mapper

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.elau.example.idempotent_post.dto.CreateItemDTO
import net.elau.example.idempotent_post.dto.CryptographicItemToken
import net.elau.example.idempotent_post.model.Item
import net.elau.example.idempotent_post.util.getBytesFromUUID
import net.elau.example.idempotent_post.web.request.CreateItemRequest
import org.springframework.util.DigestUtils
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import java.util.*

fun CreateItemRequest.toDTO(transactionToken: UUID?): CreateItemDTO {
    return CreateItemDTO(
        name = this.name,
        description = this.description,
        transactionToken = transactionToken
    )
}

fun CreateItemDTO.toModel(): Item {
    return Item(
        name = this.name,
        description = this.description,
        transactionToken = getTransactionToken(this)
    )
}

fun CreateItemDTO.toCrypto(): CryptographicItemToken {
    return CryptographicItemToken(
        name = this.name,
        description = this.description,
        timestamp = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString()
    )
}

private fun getTransactionToken(createItemDTO: CreateItemDTO): String =
    createItemDTO.transactionToken?.run {
        DigestUtils.md5DigestAsHex(getBytesFromUUID(this))
    } ?: DigestUtils.md5DigestAsHex(Json.encodeToString(createItemDTO.toCrypto()).toByteArray())
