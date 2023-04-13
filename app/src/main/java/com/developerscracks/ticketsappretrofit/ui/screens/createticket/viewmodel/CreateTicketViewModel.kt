package com.developerscracks.ticketsappretrofit.ui.screens.createticket.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.ticketsappretrofit.domain.usecases.TicketUseCases
import com.developerscracks.ticketsappretrofit.domain.utils.TicketResult
import com.developerscracks.ticketsappretrofit.ui.mapper.toTicket
import com.developerscracks.ticketsappretrofit.ui.model.TicketUI
import com.developerscracks.ticketsappretrofit.ui.utils.Constants.TICKET_STATUS_NEW
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CreateTicketViewModel @Inject constructor(private val ticketUseCases: TicketUseCases): ViewModel() {

    private var _ticket:MutableLiveData<TicketUI?> = MutableLiveData()
    val ticket: LiveData<TicketUI?> = _ticket

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error


    @RequiresApi(Build.VERSION_CODES.O)
    fun createNewTicket(
        title: String,
        name: String,
        team: String,
        incident: String,
        severity:String,
        version:String,
        description: String
    ){

        val ticket = TicketUI(
            title = title,
            date = LocalDate.now(),
            status = TICKET_STATUS_NEW,
            person = name,
            team = team,
            incident = incident,
            severity = severity,
            version = version,
            description = description
        )

        viewModelScope.launch {
            _loading.value = true
            val result = ticketUseCases.createTicketUseCase(ticket.toTicket())

            when(result){
                is TicketResult.Success ->{
                    _ticket.value = result.data
                }

                is TicketResult.Error ->{
                    _error.value = result.error.message
                }
            }

            _loading.value = false
        }
    }
}