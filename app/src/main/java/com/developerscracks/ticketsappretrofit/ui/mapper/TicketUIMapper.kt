package com.developerscracks.ticketsappretrofit.ui.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.domain.utils.toLocalDate
import com.developerscracks.ticketsappretrofit.ui.model.TicketUI

@RequiresApi(Build.VERSION_CODES.O)
fun Ticket.toTicketUI(): TicketUI {
    return TicketUI(
        title = title,
        date = date.toLocalDate(),
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
        date = date.toString(),
        status = status,
        person = person,
        team = team,
        incident = incident,
        severity = severity,
        version = version,
        description = description
    )
}