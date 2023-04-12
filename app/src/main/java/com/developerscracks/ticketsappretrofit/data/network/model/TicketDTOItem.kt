package com.developerscracks.ticketsappretrofit.data.network.model

import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.random.Random

data class TicketDTO(
    @SerializedName("idTicket") val id: String?,
    @SerializedName("numTicket") val number: String,
    @SerializedName("titleTicket") val title: String,
    @SerializedName("dateTicket") val date: String,
    @SerializedName("statusTicket") val status: String,
    @SerializedName("personInCharge") val person: String,
    @SerializedName("responsibleTeam") val team: String,
    @SerializedName("incidentType") val incident: String,
    @SerializedName("severityIncident") val severity: String,
    @SerializedName("versionSoftware") val version: String,
    @SerializedName("descriptionProblem") val description: String,
    val image1: String,
    val image2: String,
    val image3: String
)

fun TicketDTO.toDomain(): Ticket{
    return Ticket(
        id = id?.toInt() ?: 0,
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

fun Ticket.toTicketDTO(): TicketDTO{
    return TicketDTO(
        id = id.toString(),
        number = number,
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