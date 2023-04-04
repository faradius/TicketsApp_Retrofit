package com.developerscracks.ticketsappretrofit.domain.usecases

import com.developerscracks.ticketsappretrofit.data.repository.TicketRepository
import com.developerscracks.ticketsappretrofit.di.IoDispatcher
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.domain.utils.NotDataFound
import com.developerscracks.ticketsappretrofit.domain.utils.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAllTicketsUseCase @Inject constructor(
    private val ticketRepository: TicketRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Unit, List<Ticket>>(dispatcher){
    override suspend fun execute(params: Unit): List<Ticket> {
        val tickets = ticketRepository.getAllTickets()

        if (tickets.isEmpty()){
            throw NotDataFound()
        }

        return tickets
    }
}