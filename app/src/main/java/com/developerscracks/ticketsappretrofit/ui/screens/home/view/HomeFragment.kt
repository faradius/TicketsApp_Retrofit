package com.developerscracks.ticketsappretrofit.ui.screens.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developerscracks.ticketsappretrofit.databinding.FragmentHomeBinding
import com.developerscracks.ticketsappretrofit.ui.screens.home.viewmodel.HomeViewModel
import com.developerscracks.ticketsappretrofit.ui.utils.hide
import com.developerscracks.ticketsappretrofit.ui.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val ticketsAdapter: TicketsAdapter = TicketsAdapter(
        onClick = {selectedTicket ->
            val action = HomeFragmentDirections.actionHomeFragmentToTicketDetailFragment(selectedTicket)
            findNavController().navigate(action)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllTickets()

        setupRecyclerView()
        showStatusProgressBar()
        showInfoRecyclerView()
        showMessageIsError()

        binding.fabCreateTicket.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCreateTicketFragment()
            findNavController().navigate(action)
        }

    }

    private fun showMessageIsError(){
        viewModel.error.observe(viewLifecycleOwner){errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showInfoRecyclerView(){
        viewModel.tickets.observe(viewLifecycleOwner){ tickets ->
            ticketsAdapter.submitList(tickets)
        }
    }

    private fun showStatusProgressBar(){
        viewModel.loading.observe(viewLifecycleOwner){ status->
            if (status)binding.progressBar.show() else binding.progressBar.hide()
        }
    }
    private fun setupRecyclerView(){
        binding.rvTickets.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ticketsAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllTickets()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}