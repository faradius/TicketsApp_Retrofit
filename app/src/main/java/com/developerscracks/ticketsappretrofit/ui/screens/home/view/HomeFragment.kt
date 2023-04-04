package com.developerscracks.ticketsappretrofit.ui.screens.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.developerscracks.ticketsappretrofit.R
import com.developerscracks.ticketsappretrofit.core.hide
import com.developerscracks.ticketsappretrofit.core.show
import com.developerscracks.ticketsappretrofit.data.utils.TicketResult
import com.developerscracks.ticketsappretrofit.databinding.FragmentHomeBinding
import com.developerscracks.ticketsappretrofit.ui.screens.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val ticketsAdapter: TicketsAdapter = TicketsAdapter()

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

        binding.rvTickets.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ticketsAdapter
        }

        viewModel.tickets.observe(viewLifecycleOwner){ result ->
            when(result){
                is TicketResult.Loading ->{
                    binding.progressBar.show()
                }

                is TicketResult.Success ->{
                    binding.progressBar.hide()
                    ticketsAdapter.submitList(result.data)
                }

                is TicketResult.Error -> {
                    binding.progressBar.hide()
                    Toast.makeText(requireContext(), result.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}