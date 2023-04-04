package com.developerscracks.ticketsappretrofit.data.repository

import com.developerscracks.ticketsappretrofit.data.network.model.TicketDTO
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket

interface TicketRepository {
    suspend fun getAllTickets(): List<Ticket>
}