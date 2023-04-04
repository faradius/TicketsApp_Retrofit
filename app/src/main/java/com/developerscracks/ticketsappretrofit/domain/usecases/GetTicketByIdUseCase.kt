package com.developerscracks.ticketsappretrofit.domain.usecases

import com.developerscracks.ticketsappretrofit.data.repository.TicketRepository
import com.developerscracks.ticketsappretrofit.di.IoDispatcher
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.domain.utils.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetTicketByIdUseCase @Inject constructor(
    private val ticketRepository: TicketRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Int, Ticket>(dispatcher){
    override suspend fun execute(params: Int): Ticket {
        return ticketRepository.getTicketById(params)
    }

}