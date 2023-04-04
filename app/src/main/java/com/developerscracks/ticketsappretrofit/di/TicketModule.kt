package com.developerscracks.ticketsappretrofit.di

import com.developerscracks.ticketsappretrofit.data.network.datasource.TicketDatasourceNetwork
import com.developerscracks.ticketsappretrofit.data.network.datasource.TicketDatasourceNetworkImpl
import com.developerscracks.ticketsappretrofit.data.repository.TicketRepository
import com.developerscracks.ticketsappretrofit.data.repository.TicketRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TicketModule {

    @Binds
    abstract fun bindsTicketDataSourceNetwork(impl: TicketDatasourceNetworkImpl): TicketDatasourceNetwork

    @Binds
    abstract fun bindsTicketRepository(impl: TicketRepositoryImpl): TicketRepository
}