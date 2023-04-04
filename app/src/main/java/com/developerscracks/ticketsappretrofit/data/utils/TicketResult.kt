package com.developerscracks.ticketsappretrofit.data.utils

sealed class TicketResult<out R>  {
    data class Success<T>(val data: T) : TicketResult<T>()
    data class Error(val error: Exception) : TicketResult<Nothing>()
}