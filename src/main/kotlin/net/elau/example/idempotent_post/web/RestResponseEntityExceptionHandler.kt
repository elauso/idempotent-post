package net.elau.example.idempotent_post.web

import net.elau.example.idempotent_post.web.response.ErrorMessageResponse
import org.hibernate.exception.ConstraintViolationException
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.Instant.now

@RestControllerAdvice
class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    @ResponseStatus(value = CONFLICT)
    protected fun handleConflict(ex: RuntimeException, request: WebRequest) =
        ErrorMessageResponse(CONFLICT.value(), now(), ex.message, request.getDescription(false))
}
