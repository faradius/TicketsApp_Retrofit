package com.developerscracks.ticketsappretrofit.di

import com.developerscracks.ticketsappretrofit.data.repository.TicketRepository
import com.developerscracks.ticketsappretrofit.domain.usecases.GetAllTicketsUseCase
import com.developerscracks.ticketsappretrofit.domain.usecases.TicketUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun providesTicketUseCases(repository: TicketRepository, @IoDispatcher dispatcher: CoroutineDispatcher) = TicketUseCases(
        getAllTicketsUseCase = GetAllTicketsUseCase(repository, dispatcher)
    )
}