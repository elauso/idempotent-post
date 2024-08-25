package net.elau.example.idempotent_post.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Item(
    var name: String,
    var description: String,
    @Column(unique = true) var transactionToken: String,
    @Id @GeneratedValue var id: Long? = null
)