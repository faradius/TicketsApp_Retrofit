package com.developerscracks.ticketsappretrofit.ui.screens.detailticket.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.developerscracks.ticketsappretrofit.core.hide
import com.developerscracks.ticketsappretrofit.core.show
import com.developerscracks.ticketsappretrofit.databinding.FragmentTicketDetailBinding
import com.developerscracks.ticketsappretrofit.ui.screens.detailticket.viewmodel.TicketDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketDetailFragment : Fragment() {

    private var _binding: FragmentTicketDetailBinding? = null
    private val binding get() = _binding!!

    private val args: TicketDetailFragmentArgs by navArgs()

    private val viewModel: TicketDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTicketDetail(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showStatusProgressBar()
        showInfoDetailTicket()
        showMessageIsError()
    }

    private fun showMessageIsError() {
        viewModel.error.observe(viewLifecycleOwner){ errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showStatusProgressBar() {
        viewModel.loading.observe(viewLifecycleOwner){ status->
            if (status){
                binding.progressBar.show()
                binding.containerTicketDetail.hide()
            } else {
                binding.progressBar.hide()
                binding.containerTicketDetail.show()
            }
        }
    }

    private fun showInfoDetailTicket(){
        viewModel.ticketDetail.observe(viewLifecycleOwner){ticket->
            binding.apply {
                tvTitleTicket.text = ticket.title
                tvNumTicket.text = ticket.number
                tvIdTicket.text = "${ticket.id}"
                tvDateTicket.text = ticket.date
                tvNamePersonInChargeTicket.text = ticket.person
                tvResponsibleTeam.text = ticket.team
                tvincidentTypeTicket.text = ticket.incident
                tvSeverityIncident.text = ticket.severity
                tvVersionSoftware.text = ticket.version
                tvDescriptionProblem.text = ticket.description
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}