package net.elau.example.idempotent_post.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.Instant

@Entity
class Item(
    var name: String,
    var description: String,
    var requestDate: Instant,
    @Column(unique = true) var transactionToken: String,
    @Id @GeneratedValue var id: Long? = null
)