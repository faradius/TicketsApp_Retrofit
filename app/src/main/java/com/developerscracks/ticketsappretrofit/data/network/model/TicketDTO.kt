package com.developerscracks.ticketsappretrofit.data.network.model

import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.google.gson.annotations.SerializedName
import java.util.*

data class TicketDTO(
    @SerializedName("idTicket") val id: String?,
    @SerializedName("numTicket") val number: String?,
    @SerializedName("titleTicket") val title: String,
    @SerializedName("dateTicket") val date: String,
    @SerializedName("statusTicket") val status: String,
    @SerializedName("personInCharge") val person: String,
    @SerializedName("responsibleTeam") val team: String,
    @SerializedName("incidentType") val incident: String,
    @SerializedName("severityIncident") val severity: String,
    @SerializedName("versionSoftware") val version: String,
    @SerializedName("descriptionProblem") val description: String,
    val image1: String?,
    val image2: String?,
    val image3: String?
)