package com.developerscracks.ticketsappretrofit.data.network.datasource

import com.developerscracks.ticketsappretrofit.data.network.model.TicketDTO
import com.developerscracks.ticketsappretrofit.data.utils.ApiResponse

interface TicketDatasourceNetwork {
    suspend fun getAllTickets():ApiResponse<List<TicketDTO>>
    suspend fun getTicketById(id: Int):ApiResponse<TicketDTO>
}