package com.developerscracks.ticketsappretrofit.domain.usecases

import com.developerscracks.ticketsappretrofit.data.repository.TicketRepository
import com.developerscracks.ticketsappretrofit.di.IoDispatcher
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.domain.utils.UseCase
import com.developerscracks.ticketsappretrofit.ui.mapper.toTicketUI
import com.developerscracks.ticketsappretrofit.ui.model.TicketUI
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CreateTicketUseCase @Inject constructor(
    private val ticketRepository: TicketRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Ticket, TicketUI>(dispatcher) {
    override suspend fun execute(params: Ticket): TicketUI {
        return ticketRepository.createTicket(params).toTicketUI()
    }
}