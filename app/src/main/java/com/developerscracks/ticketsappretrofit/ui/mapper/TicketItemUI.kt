package com.developerscracks.ticketsappretrofit.ui.mapper

import com.developerscracks.ticketsappretrofit.domain.entities.Ticket

data class TicketItemUI(
    val id: Int,
    val number: String,
    val title: String,
    val incident: String,
    val severity: String
)

fun Ticket.toTicketItemUI(): TicketItemUI{
    return TicketItemUI(
        id = id,
        number = number,
        title = title,
        incident = incident,
        severity = severity
    )
}
