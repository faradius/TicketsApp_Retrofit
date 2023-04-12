package com.developerscracks.ticketsappretrofit.data.repository

import com.developerscracks.ticketsappretrofit.domain.entities.Ticket

interface TicketRepository {
    suspend fun getAllTickets(): List<Ticket>
    suspend fun getTicketById(id: Int): Ticket

    suspend fun createTicket(ticket: Ticket): Ticket
}