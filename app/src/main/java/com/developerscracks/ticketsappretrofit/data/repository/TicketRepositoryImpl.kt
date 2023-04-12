package com.developerscracks.ticketsappretrofit.data.repository

import com.developerscracks.ticketsappretrofit.data.mapper.dto.toDomain
import com.developerscracks.ticketsappretrofit.data.mapper.dto.toTicketDTO
import com.developerscracks.ticketsappretrofit.data.network.datasource.TicketDatasourceNetwork
import com.developerscracks.ticketsappretrofit.data.utils.ApiResponse
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val networkDatasource: TicketDatasourceNetwork
): TicketRepository {
    override suspend fun getAllTickets(): List<Ticket> {
        return when (val response = networkDatasource.getAllTickets()){
            is ApiResponse.ApiEmptyResponse -> throw NotImplementedError("The response is empty")
            is ApiResponse.ApiError -> throw NotImplementedError("The server responded with an error: ${response.errorMessage}")
            is ApiResponse.ApiSuccessResponse -> {
                response.body.map { it.toDomain() }
            }
        }
    }

    override suspend fun getTicketById(id: Int): Ticket {
        return when (val response = networkDatasource.getTicketById(id)){
            is ApiResponse.ApiEmptyResponse -> throw NotImplementedError("The response is empty")
            is ApiResponse.ApiError -> throw NotImplementedError("The server responded with an error: ${response.errorMessage}")
            is ApiResponse.ApiSuccessResponse -> {
                response.body.toDomain()
            }
        }
    }

    override suspend fun createTicket(ticket: Ticket): Ticket {
        return when (val response = networkDatasource.createTicket(ticket.toTicketDTO())){
            is ApiResponse.ApiEmptyResponse -> throw NotImplementedError("The response is empty")
            is ApiResponse.ApiError -> throw NotImplementedError("The server responded with an error: ${response.errorMessage}")
            is ApiResponse.ApiSuccessResponse -> {
                response.body.toDomain()
            }
        }
    }
}