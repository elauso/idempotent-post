package net.elau.example.idempotent_post.service

import jakarta.transaction.Transactional
import net.elau.example.idempotent_post.dto.CreateItemDTO
import net.elau.example.idempotent_post.mapper.toModel
import net.elau.example.idempotent_post.repository.ItemRepository
import org.springframework.stereotype.Service

@Service
@Transactional
class ItemService(private val repository: ItemRepository) {

    fun create(createItemDTO: CreateItemDTO) {
        repository.save(createItemDTO.toModel())
    }
}