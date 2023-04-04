package com.developerscracks.ticketsappretrofit.ui.screens.ticketdetail.viewmodel

import android.arch.lifecycle.Transformations
import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.ticketsappretrofit.data.utils.TicketResult
import com.developerscracks.ticketsappretrofit.domain.entities.Ticket
import com.developerscracks.ticketsappretrofit.domain.usecases.TicketUseCases
import com.developerscracks.ticketsappretrofit.ui.mapper.TicketDetailUI
import com.developerscracks.ticketsappretrofit.ui.mapper.toTicketDetailUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketDetailViewModel @Inject constructor(private val ticketUseCases: TicketUseCases): ViewModel() {

    private val _ticketDetail: MutableLiveData<TicketResult<TicketDetailUI>> = MutableLiveData(TicketResult.Loading())
    val ticketDetail: LiveData<TicketResult<TicketDetailUI>> = _ticketDetail

    fun getTicketDetail(id:Int){
        viewModelScope.launch {
            val result = ticketUseCases.getTicketByIdUseCase(id)
            when(result){
                is TicketResult.Loading ->{
                    _ticketDetail.value = TicketResult.Loading()
                }

                is TicketResult.Success ->{
                    val ticket = result.data.toTicketDetailUI()
                    _ticketDetail.value = TicketResult.Success(ticket)
                }
                is TicketResult.Error -> {
                    _ticketDetail.value = TicketResult.Error(result.error)
                }
            }
        }
    }
}