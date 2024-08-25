package net.elau.example.idempotent_post.repository

import net.elau.example.idempotent_post.model.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<Item, Long> {
}