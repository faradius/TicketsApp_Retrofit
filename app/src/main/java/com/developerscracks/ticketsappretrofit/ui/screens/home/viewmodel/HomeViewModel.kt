package com.developerscracks.ticketsappretrofit.ui.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.ticketsappretrofit.data.utils.TicketResult
import com.developerscracks.ticketsappretrofit.domain.usecases.TicketUseCases
import com.developerscracks.ticketsappretrofit.ui.mapper.TicketItemUI
import com.developerscracks.ticketsappretrofit.ui.mapper.toTicketItemUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val ticketUseCases: TicketUseCases): ViewModel() {

    private val _tickets: MutableLiveData<TicketResult<List<TicketItemUI>>> = MutableLiveData()
    val tickets: LiveData<TicketResult<List<TicketItemUI>>> = _tickets

    fun getAllTickets(){
        viewModelScope.launch {
            val result = ticketUseCases.getAllTicketsUseCase(Unit)
            when(result){
                is TicketResult.Loading ->{
                    _tickets.value = TicketResult.Loading()
                }
                is TicketResult.Success ->{
                    val tickets = result.data.map { it.toTicketItemUI() }
                    _tickets.value = TicketResult.Success(tickets)
                }
                is TicketResult.Error ->{
                    _tickets.value = TicketResult.Error(result.error)
                }
            }
        }
    }
}