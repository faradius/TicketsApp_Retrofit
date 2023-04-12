package com.developerscracks.ticketsappretrofit.ui.screens.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerscracks.ticketsappretrofit.databinding.TicketItemLayoutBinding
import com.developerscracks.ticketsappretrofit.ui.model.TicketItemUI

class TicketsAdapter(private val onClick: (Int) -> Unit): ListAdapter<TicketItemUI, TicketsAdapter.TicketViewHolder>(TicketDiffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(TicketItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = getItem(position)
        holder.bind(ticket)
    }

    inner class TicketViewHolder(
        itemBinding: TicketItemLayoutBinding
    ): RecyclerView.ViewHolder(itemBinding.root){

        private val number = itemBinding.tvNumTicket
        private val title = itemBinding.tvTitleTicket
        private val severity = itemBinding.tvSeverityIncident
        private val incident = itemBinding.tvPriorityTicket

        private var currentTicket: TicketItemUI? = null

        init {
            itemView.setOnClickListener {
                currentTicket?.let {
                    onClick(it.id!!)
                }
            }
        }

        fun bind(ticket: TicketItemUI){
            currentTicket = ticket

            number.text = ticket.id.toString()
            title.text = ticket.title
            severity.text = ticket.severity
            incident.text = ticket.incident
        }
    }

}

object TicketDiffUtil: DiffUtil.ItemCallback<TicketItemUI>(){
    override fun areItemsTheSame(oldItem: TicketItemUI, newItem: TicketItemUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TicketItemUI, newItem: TicketItemUI): Boolean {
        return oldItem == newItem
    }

}