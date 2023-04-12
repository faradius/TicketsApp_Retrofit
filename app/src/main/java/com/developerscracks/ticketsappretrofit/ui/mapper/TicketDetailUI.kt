package com.developerscracks.ticketsappretrofit.ui.mapper

import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.ui.model.TicketDetailUI

fun Ticket.toTicketDetailUI(): TicketDetailUI {
    return TicketDetailUI(
        id = id,
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
