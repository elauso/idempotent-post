package net.elau.example.idempotent_post

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IdempotentPostApplication

fun main(args: Array<String>) {
	runApplication<IdempotentPostApplication>(*args)
}
