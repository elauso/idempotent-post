package net.elau.example.idempotent_post.service

import jakarta.transaction.Transactional
import net.elau.example.idempotent_post.dto.CreateItemDTO
import net.elau.example.idempotent_post.exception.InvalidRequestDateException
import net.elau.example.idempotent_post.mapper.toModel
import net.elau.example.idempotent_post.repository.ItemRepository
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Duration.ofMinutes
import java.time.Instant
import java.time.OffsetDateTime.now
import java.time.temporal.ChronoUnit

@Service
@Transactional
class ItemService(private val repository: ItemRepository) {
    companion object {
        const val MAX_MORE_OLD_REQUEST_DATE_MINUTES = 5
    }

    fun create(createItemDTO: CreateItemDTO) {
        createItemDTO.apply { validateRequestDate(this) }
            .run { repository.save(this.toModel()) }
    }

    private fun validateRequestDate(createItemDTO: CreateItemDTO) {
        val requestDate = createItemDTO.requestDate

        if (requestDate.isAfter(now().truncatedTo(ChronoUnit.MINUTES).toInstant())) {
            throw InvalidRequestDateException("The request-date must not be after current time")
        }

        if (Duration.between(requestDate, Instant.now()) > ofMinutes(MAX_MORE_OLD_REQUEST_DATE_MINUTES.toLong())) {
            throw InvalidRequestDateException("The request-date must not be more old than $MAX_MORE_OLD_REQUEST_DATE_MINUTES minutes ago. Please try again")
        }
    }
}
