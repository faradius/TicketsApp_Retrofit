package com.developerscracks.ticketsappretrofit.ui.screens.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.ticketsappretrofit.domain.utils.TicketResult
import com.developerscracks.ticketsappretrofit.domain.usecases.TicketUseCases
import com.developerscracks.ticketsappretrofit.ui.mapper.TicketItemUI
import com.developerscracks.ticketsappretrofit.ui.mapper.toTicketItemUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val ticketUseCases: TicketUseCases): ViewModel() {

    private val _tickets: MutableLiveData<List<TicketItemUI>> = MutableLiveData()
    val tickets: LiveData<List<TicketItemUI>> = _tickets

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getAllTickets(){
        viewModelScope.launch {
            _loading.value = true

            val result = ticketUseCases.getAllTicketsUseCase(Unit)
            when(result){
                is TicketResult.Success ->{
                    val tickets = result.data.map { it.toTicketItemUI() }
                    _tickets.value = tickets
                }
                is TicketResult.Error ->{
                    _error.value = when(result.error){
                        is HttpException ->{
                            "Error del servidor"
                        }else -> "Error desconocido"
                    }
                }
            }

            _loading.value = false
        }
    }
}