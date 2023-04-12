package com.developerscracks.ticketsappretrofit.ui.mapper

import com.developerscracks.ticketsappretrofit.domain.entities.Ticket

data class TicketUI(
    val number: String,
    val title: String,
    val date: String,
    val status: String,
    val person: String,
    val team: String,
    val incident: String,
    val severity: String,
    val version: String,
    val description: String
)


fun Ticket.toTicketUI(): TicketUI{
    return TicketUI(
        number = number,
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

/*data class TicketUI(
    val number: String,
    val title: String,
    val date: String,
    val status: String,
    val person: String,
    val team: String,
    val incident: String,
    val severity: String,
    val version: String,
    val description: String,
    val image1: String,
    val image2: String,
    val image3: String
)*/
