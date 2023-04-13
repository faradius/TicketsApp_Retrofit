package com.developerscracks.ticketsappretrofit.ui.screens.createticket.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.developerscracks.ticketsappretrofit.R
import com.developerscracks.ticketsappretrofit.databinding.FragmentCreateTicketBinding
import com.developerscracks.ticketsappretrofit.ui.screens.createticket.viewmodel.CreateTicketViewModel
import com.developerscracks.ticketsappretrofit.ui.utils.Constants.TICKET_CREATE_SUCCESFULL
import com.developerscracks.ticketsappretrofit.ui.utils.hide
import com.developerscracks.ticketsappretrofit.ui.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTicketFragment: Fragment() {

    private var _binding: FragmentCreateTicketBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateTicketViewModel by viewModels()

    lateinit var title: String
    lateinit var name: String
    var team: String = ""
    var incident: String = ""
    var severity: String = ""
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinners()
        setItemSpinners()
        showStatusProgressBar()
        binding.btnCreateTicket.setOnClickListener { createTicket() }
        isTicketCreated()

    }

    private fun isTicketCreated(){
        viewModel.ticket.observe(viewLifecycleOwner){ ticket ->
            if (ticket != null){
                Toast.makeText(requireContext(), TICKET_CREATE_SUCCESFULL, Toast.LENGTH_SHORT).show()

                binding.apply {
                    etTitleTicketInput.setText("")
                    etNameInChargeInput.setText("")
                    spinnerResponsibleTeam.editText?.setText("")
                    team = ""
                    spinnerIncidentType.editText?.setText("")
                    incident = ""
                    spinnerSeverityIncident.editText?.setText("")
                    severity = ""
                    etVersionSoftwareInput.setText("")
                    etDescriptionProblemInput.setText("")
                }
            }else{
                viewModel.error.observe(viewLifecycleOwner){error ->
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createTicket(){
        if (validateForm()){
            title = binding.etTitleTicketInput.text.toString()
            name = binding.etNameInChargeInput.text.toString()
            version = binding.etVersionSoftwareInput.text.toString()
            description = binding.etDescriptionProblemInput.text.toString()

            viewModel.createNewTicket(title,name,team,incident,severity,version,description)
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true
        with(binding) {

            if (etTitleTicketInput.text.toString().isBlank()){
                isValid = false
                etTitleTicketInputLayaout.error = getString(R.string.form_required_field)
            }else{
                etTitleTicketInputLayaout.error = null
            }

            if (etNameInChargeInput.text.toString().isBlank()){
                isValid = false
                etNameInChargeInputLayaout.error = getString(R.string.form_required_field)
            }else{
                etNameInChargeInputLayaout.error = null
            }

            if(team.isBlank()){
                isValid = false
                spinnerResponsibleTeam.error = getString(R.string.form_required_field)
            }else{
                spinnerResponsibleTeam.error = null
            }

            if(incident.isBlank()){
                isValid = false
                spinnerIncidentType.error = getString(R.string.form_required_field)
            }else{
                spinnerIncidentType.error = null
            }

            if (severity.isBlank()){
                isValid = false
                spinnerSeverityIncident.error = getString(R.string.form_required_field)
            }else{
                spinnerSeverityIncident.error = null
            }

            if (etVersionSoftwareInput.text.toString().isBlank()){
                isValid = false
                etVersionSoftwareInputLayaout.error = getString(R.string.form_required_field)
            }else{
                etVersionSoftwareInputLayaout.error = null
            }

            if (etDescriptionProblemInput.text.toString().isBlank()){
                isValid = false
                etDescriptionProblemInputLayaout.error = getString(R.string.form_required_field)
            }else{
                etDescriptionProblemInputLayaout.error = null
            }
        }
        return isValid
    }

    private fun setItemSpinners(){
        binding.autResponsibibleTeam.setOnItemClickListener { parent, view, position, id ->
            team = parent.getItemAtPosition(position).toString()
        }

        binding.autncidentType.setOnItemClickListener { parent, view, position, id ->
            incident = parent.getItemAtPosition(position).toString()
        }

        binding.autSeverityIncident.setOnItemClickListener { parent, view, position, id ->
            severity = parent.getItemAtPosition(position).toString()
        }
    }

    private fun setupSpinners(){
        val teamAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.responsible_team,R.layout.drop_down_item)
        binding.autResponsibibleTeam.setAdapter(teamAdapter)

        val incidenAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.incident_type, R.layout.drop_down_item)
        binding.autncidentType.setAdapter(incidenAdapter)

        val severityAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.severity_incident, R.layout.drop_down_item)
        binding.autSeverityIncident.setAdapter(severityAdapter)
    }

    private fun showStatusProgressBar() {
        viewModel.loading.observe(viewLifecycleOwner){ status->
            if (status){
                binding.progressBar.show()
            } else {
                binding.progressBar.hide()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}