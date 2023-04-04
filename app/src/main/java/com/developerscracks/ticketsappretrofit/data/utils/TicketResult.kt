package com.developerscracks.ticketsappretrofit.data.utils

sealed class TicketResult<out R>  {
    class Loading<out T>: TicketResult<T>()
    data class Success<T>(val data: T) : TicketResult<T>()
    data class Error(val error: Exception) : TicketResult<Nothing>()
}