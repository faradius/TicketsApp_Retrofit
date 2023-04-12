package com.developerscracks.ticketsappretrofit.domain.usecases

data class TicketUseCases (
    val getAllTicketsUseCase: GetAllTicketsUseCase,
    val getTicketByIdUseCase: GetTicketByIdUseCase,
    val createTicketUseCase: CreateTicketUseCase
)