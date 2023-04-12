package com.developerscracks.ticketsappretrofit.ui.mapper

import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.ui.model.TicketItemUI

fun Ticket.toTicketItemUI(): TicketItemUI {
    return TicketItemUI(
        id = id,
        title = title,
        incident = incident,
        severity = severity
    )
}
