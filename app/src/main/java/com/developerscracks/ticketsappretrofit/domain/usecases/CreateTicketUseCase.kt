package com.developerscracks.ticketsappretrofit.domain.usecases

import com.developerscracks.ticketsappretrofit.data.network.model.TicketDTO
import com.developerscracks.ticketsappretrofit.data.repository.TicketRepository
import com.developerscracks.ticketsappretrofit.di.IoDispatcher
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.domain.utils.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CreateTicketUseCase @Inject constructor(
    private val ticketRepository: TicketRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<TicketDTO,Ticket>(dispatcher) {
    override suspend fun execute(params: TicketDTO): Ticket {
        return ticketRepository.createTicket(params)
    }
}