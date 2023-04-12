package com.developerscracks.ticketsappretrofit.ui.screens.createticket.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.developerscracks.ticketsappretrofit.R
import com.developerscracks.ticketsappretrofit.databinding.FragmentCreateTicketBinding
import com.developerscracks.ticketsappretrofit.ui.screens.createticket.viewmodel.CreateTicketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTicketFragment: Fragment() {

    private var _binding: FragmentCreateTicketBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateTicketViewModel by viewModels()

    lateinit var title: String
    lateinit var name: String
    lateinit var team: String
    lateinit var incident: String
    lateinit var severity:String
    lateinit var version: String
    lateinit var description: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinners()

        binding.autResponsibibleTeam.setOnItemClickListener { parent, view, position, id ->
            team = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Item: $team", Toast.LENGTH_SHORT).show()
        }

        binding.autncidentType.setOnItemClickListener { parent, view, position, id ->
            incident = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Item: $incident", Toast.LENGTH_SHORT).show()
        }

        binding.autSeverityIncident.setOnItemClickListener { parent, view, position, id ->
            severity = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Item: $severity", Toast.LENGTH_SHORT).show()
        }

        binding.btnCreateTicket.setOnClickListener {
            title = binding.etTitleTicketInput.text.toString()
            name = binding.etNameInChargeInput.text.toString()
            version = binding.etVersionSoftwareInput.text.toString()
            description = binding.etDescriptionProblemInput.text.toString()

            viewModel.createNewTicket(title,name,team,incident,severity,version,description)
        }

        viewModel.ticket.observe(viewLifecycleOwner){
            if (it != null){
                Toast.makeText(requireContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Hubo un Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSpinners(){
        val teamAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.responsible_team,R.layout.drop_down_item)
        binding.autResponsibibleTeam.setAdapter(teamAdapter)

        val incidenAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.incident_type, R.layout.drop_down_item)
        binding.autncidentType.setAdapter(incidenAdapter)

        val severityAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.severity_incident, R.layout.drop_down_item)
        binding.autSeverityIncident.setAdapter(severityAdapter)
    }
}