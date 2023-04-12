package com.developerscracks.ticketsappretrofit.data.mapper.dto

import com.developerscracks.ticketsappretrofit.data.network.model.TicketDTO
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket

fun TicketDTO.toDomain(): Ticket {
    return Ticket(
        id = id?.toInt(),
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

fun Ticket.toTicketDTO(): TicketDTO {
    return TicketDTO(
        id = id.toString(),
        number = "",
        title = title,
        date = date,
        status = status,
        person = person,
        team = team,
        incident = incident,
        severity = severity,
        version = version,
        description = description,
        image1 = "",
        image2 = "",
        image3 = ""
    )
}