package com.developerscracks.ticketsappretrofit.ui.model

data class TicketUI(
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