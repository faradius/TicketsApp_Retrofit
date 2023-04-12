package com.developerscracks.ticketsappretrofit.ui.mapper

import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.ui.model.TicketUI

fun Ticket.toTicketUI(): TicketUI {
    return TicketUI(
        title = title,
        date = date,
        status = status,
        person = person,
        team = team,
        incident = incident,
        severity = severity,
        version = version,
        description = description
    )
}

fun TicketUI.toTicket(): Ticket{
    return Ticket(
        id = null,
        title = title,
        date = date,
        status = status,
        person = person,
        team = team,
        incident = incident,
        severity = severity,
        version = version,
        description = description
    )
}