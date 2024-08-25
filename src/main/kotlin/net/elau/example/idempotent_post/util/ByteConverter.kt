package net.elau.example.idempotent_post.util

import java.nio.ByteBuffer
import java.util.*

fun getBytesFromUUID(uuid: UUID): ByteArray {
    val bb: ByteBuffer = ByteBuffer.wrap(ByteArray(16))
    bb.putLong(uuid.mostSignificantBits)
    bb.putLong(uuid.leastSignificantBits)
    return bb.array()
}