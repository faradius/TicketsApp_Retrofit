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

    private val _tickets: MutableLiveData<List<TicketItemUI>> = MutableLiveData()
    val tickets: LiveData<List<TicketItemUI>> = _tickets

    fun getAllTickets(){
        viewModelScope.launch {
            val result = ticketUseCases.getAllTicketsUseCase(Unit)
            when(result){
                is TicketResult.Success ->{
                    _tickets.value = result.data.map { it.toTicketItemUI() }
                }
                is TicketResult.Error ->{
                    Log.e("ERROR", result.error.message ?: "Error desconocido")
                }
            }
        }
    }
}