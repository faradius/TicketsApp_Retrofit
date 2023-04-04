package com.developerscracks.ticketsappretrofit.data.network.datasource

import com.developerscracks.ticketsappretrofit.data.network.api.ApiService
import com.developerscracks.ticketsappretrofit.data.network.model.TicketDTO
import com.developerscracks.ticketsappretrofit.data.utils.ApiResponse
import com.developerscracks.ticketsappretrofit.data.utils.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TicketDatasourceNetworkImpl @Inject constructor(private val api: ApiService): TicketDatasourceNetwork{
    override suspend fun getAllTickets(): ApiResponse<List<TicketDTO>> {
        return withContext(Dispatchers.IO){
            api.getAllTickets().handleResponse()
        }
    }
}