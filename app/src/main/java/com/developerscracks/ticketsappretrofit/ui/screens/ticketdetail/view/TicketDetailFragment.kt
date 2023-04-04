package com.developerscracks.ticketsappretrofit.ui.screens.ticketdetail.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.developerscracks.ticketsappretrofit.R
import com.developerscracks.ticketsappretrofit.core.hide
import com.developerscracks.ticketsappretrofit.core.show
import com.developerscracks.ticketsappretrofit.data.utils.TicketResult
import com.developerscracks.ticketsappretrofit.databinding.FragmentTicketDetailBinding
import com.developerscracks.ticketsappretrofit.ui.screens.ticketdetail.viewmodel.TicketDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketDetailFragment : Fragment() {

    private var _binding: FragmentTicketDetailBinding? = null
    private val binding get() = _binding!!

    private val args: TicketDetailFragmentArgs by navArgs()

    private val viewModel: TicketDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTicketDetail(args.id)

        viewModel.ticketDetail.observe(viewLifecycleOwner) { ticket ->
            when (ticket) {
                is TicketResult.Loading -> {
                    binding.progressBar.show()
                    binding.containerTicketDetail.hide()
                }

                is TicketResult.Success -> {
                    binding.apply {
                        progressBar.hide()
                        containerTicketDetail.show()

                        tvTitleTicket.text = ticket.data.title
                        tvNumTicket.text = ticket.data.number
                        tvIdTicket.text = "${ticket.data.id}"
                        tvDateTicket.text = ticket.data.date
                        tvNamePersonInChargeTicket.text = ticket.data.person
                        tvResponsibleTeam.text = ticket.data.team
                        tvincidentTypeTicket.text = ticket.data.incident
                        tvSeverityIncident.text = ticket.data.severity
                        tvVersionSoftware.text = ticket.data.version
                        tvDescriptionProblem.text = ticket.data.description
                    }
                }

                is TicketResult.Error -> {
                    binding.progressBar.show()
                    binding.containerTicketDetail.hide()
                    Toast.makeText(requireContext(), ticket.error.message, Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {}
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}