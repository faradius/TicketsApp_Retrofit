package com.developerscracks.ticketsappretrofit.ui.screens.createticket.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.ticketsappretrofit.data.network.model.TicketDTO
import com.developerscracks.ticketsappretrofit.data.network.model.toTicketDTO
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.domain.usecases.TicketUseCases
import com.developerscracks.ticketsappretrofit.domain.utils.TicketResult
import com.developerscracks.ticketsappretrofit.ui.mapper.TicketUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CreateTicketViewModel @Inject constructor(private val ticketUseCases: TicketUseCases): ViewModel() {

    private var _ticket:MutableLiveData<TicketDTO> = MutableLiveData()
    val ticket: LiveData<TicketDTO> = _ticket


    fun createNewTicket(
        title: String,
        name: String,
        team: String,
        incident: String,
        severity:String,
        version:String,
        description: String
    ){

        val ticket = TicketDTO(
            id = null,
            number = "numero",
            title = title,
            date = "fecha",
            status = "NEW",
            person = name,
            team = team,
            incident = incident,
            severity = severity,
            version = version,
            description = description,
            image1 = "",
            image2 = "",
            image3 = ""

        )

        viewModelScope.launch {
            val result = ticketUseCases.createTicketUseCase(ticket)

            when(result){
                is TicketResult.Success ->{
                    _ticket.value = result.data.toTicketDTO()
                }

                is TicketResult.Error ->{
                    Log.d("TAG", "createNewTicket: Error desconocido")
                }
            }
        }
    }
}