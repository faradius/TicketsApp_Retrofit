package com.developerscracks.ticketsappretrofit.ui.screens.detailticket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.ticketsappretrofit.domain.utils.TicketResult
import com.developerscracks.ticketsappretrofit.domain.usecases.TicketUseCases
import com.developerscracks.ticketsappretrofit.ui.mapper.toTicketDetailUI
import com.developerscracks.ticketsappretrofit.ui.model.TicketDetailUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketDetailViewModel @Inject constructor(private val ticketUseCases: TicketUseCases): ViewModel() {

    private val _ticketDetail: MutableLiveData<TicketDetailUI> = MutableLiveData()
    val ticketDetail: LiveData<TicketDetailUI> = _ticketDetail

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getTicketDetail(id:Int){
        viewModelScope.launch {
            _loading.value = true

            val result = ticketUseCases.getTicketByIdUseCase(id)
            when(result){
                is TicketResult.Success ->{
                    val ticket = result.data.toTicketDetailUI()
                    _ticketDetail.value = ticket
                }
                is TicketResult.Error -> {
                    _error.value = result.error.message
                }
            }

            _loading.value = false
        }
    }
}