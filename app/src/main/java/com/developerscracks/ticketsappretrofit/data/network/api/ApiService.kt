package com.developerscracks.ticketsappretrofit.data.network.api

import com.developerscracks.ticketsappretrofit.core.Config.GET_ALL_TICKETS
import com.developerscracks.ticketsappretrofit.data.network.model.TicketDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(GET_ALL_TICKETS)
    suspend fun getAllTickets():Response<List<TicketDTO>>
}