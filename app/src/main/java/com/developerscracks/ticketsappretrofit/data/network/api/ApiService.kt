package com.developerscracks.ticketsappretrofit.data.network.api

import com.developerscracks.ticketsappretrofit.core.Config.CREATE_NEW_TICKET
import com.developerscracks.ticketsappretrofit.core.Config.GET_ALL_TICKETS
import com.developerscracks.ticketsappretrofit.core.Config.GET_TICKET_BY_ID
import com.developerscracks.ticketsappretrofit.data.network.model.TicketDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET(GET_ALL_TICKETS)
    suspend fun getAllTickets():Response<List<TicketDTO>>

    @GET(GET_TICKET_BY_ID)
    suspend fun getTicketById(@Path("idTicket") id:Int):Response<TicketDTO>

    @POST(CREATE_NEW_TICKET)
    suspend fun createNewTicket(@Body ticket: TicketDTO): Response<TicketDTO>
}