package com.developerscracks.ticketsappretrofit.data.utils

import retrofit2.Response

sealed class ApiResponse<T>{

    class ApiEmptyResponse<T>: ApiResponse<T>()

    data class ApiError<T>(val errorMessage: String): ApiResponse<T>()

    data class ApiSuccessResponse<T>(val body: T): ApiResponse<T>()
}


fun <T> Response<T>.handleResponse(): ApiResponse<T> {
    return if (isSuccessful) {
        val body = body()
        if (body == null || code() == 204) {
            ApiResponse.ApiEmptyResponse()
        } else {
            ApiResponse.ApiSuccessResponse(body)
        }
    } else {
        val code = code()
        val msg = errorBody()?.string()
        val errorMsg = if (msg.isNullOrEmpty()) {
            message()
        } else {
            msg
        }
        when (code) {
            400 -> ApiResponse.ApiError("Bad request")
            401 -> ApiResponse.ApiError("Unauthorized")
            403 -> ApiResponse.ApiError("Forbidden")
            404 -> ApiResponse.ApiError("Not found")
            else -> ApiResponse.ApiError(errorMsg)
        }
    }

}