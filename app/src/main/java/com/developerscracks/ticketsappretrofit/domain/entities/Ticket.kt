package com.developerscracks.ticketsappretrofit.domain.entities

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Ticket(
    val id: Int,
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
