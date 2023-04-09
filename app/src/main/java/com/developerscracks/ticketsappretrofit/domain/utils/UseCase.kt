package com.developerscracks.ticketsappretrofit.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(params: P): TicketResult<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(params).let {
                    TicketResult.Success(it)
                }
            }

        } catch (e: Exception) {
            TicketResult.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: P): R

}