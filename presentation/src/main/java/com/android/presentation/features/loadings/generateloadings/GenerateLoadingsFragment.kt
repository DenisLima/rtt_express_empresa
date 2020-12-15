package com.android.presentation.features.loadings.generateloadings

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.android.domain.features.loadings.models.Client
import com.android.domain.features.loadings.models.Vehicle
import com.android.presentation.R
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseFragment
import com.android.presentation.features.loadings.generateloadings.registrationdata.PLoading
import kotlinx.android.synthetic.main.fragment_generate_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class GenerateLoadingsFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

    private val viewModel by viewModel<GenerateLoadingsFragmentViewModel>()
    val myCalendar = Calendar.getInstance()
    private var listClient = listOf<Client>()
    private var currentyClient = 0
    private var listVehicleType = listOf<Vehicle>()
    private var currentyVehicle = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_generate_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        viewModel.getClients()
        viewModel.getVehicles()
        prepareObservers()
        enableButtonNext()
    }

    private fun initComponents() {

        btnLoadingNext.setOnClickListener {
            viewModel.mapperObjectToNavigation(
                PLoading(
                    quantity = etLoadingQtd.text.toString().toInt(),
                    client = currentyClient,
                    description = etLoadingDescription.text.toString(),
                    withdrawalHour = etLoadingWithdrawalHour.text.toString(),
                    withdrawalDate = etLoadingWithdrawal.text.toString(),
                    price = etLoadingValuePropose.text.toString().toFloat(),
                    vehicleType = currentyVehicle,
                    observation = etLoadingObservation.text.toString()
                )
            )
            navControllerLoadings.navigate(R.id.action_generateLoadingsFragment_to_generateLoadingsFormFragment)
        }

        etLoadingDateRegister.setOnClickListener {
            DatePickerDialog(
                requireContext(), date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        etLoadingWithdrawal.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateWithdrawal, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        etLoadingWithdrawalHour.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
            val minute = mcurrentTime[Calendar.MINUTE]
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(requireContext(),
                OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    etLoadingWithdrawalHour.setText(
                        "$selectedHour:$selectedMinute"
                    )
                }, hour, minute, true
            ) //Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }


    }

    private fun prepareObservers() {
        viewModel.getErrorMessage()
            .observeOn(this) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }

        viewModel.getCustomersList()
            .observeOn(this) {
                listClient = it
                currentyClient = listClient[0].id
                initSpinner(it)
            }

        viewModel.getLoading()
            .observeOn(this) {
                if (it) {
                    progressLoadingClients.visibility = View.VISIBLE
                    spinnerLoadingClients.visibility = View.INVISIBLE

                    progressLoadingVehicles.visibility = View.VISIBLE
                    spinnerLoadingVehicles.visibility = View.INVISIBLE
                } else {
                    spinnerLoadingClients.visibility = View.VISIBLE
                    progressLoadingClients.visibility = View.GONE

                    progressLoadingVehicles.visibility = View.GONE
                    spinnerLoadingVehicles.visibility = View.VISIBLE
                }
            }

        viewModel.getVehiclesList()
            .observeOn(this) {
                initSpinnerVehicles(it)
                listVehicleType = it
                currentyVehicle = listVehicleType[0].type
            }

    }

    private fun initSpinnerVehicles(vehiclesList: List<Vehicle>) {
        var spinnerAdapterVehicle =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, vehiclesList.map { it.name })
        spinnerAdapterVehicle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spinnerLoadingVehicles) {
            adapter = spinnerAdapterVehicle
            setSelection(0, true)
            onItemSelectedListener = this@GenerateLoadingsFragment
        }
    }

    private fun initSpinner(costumersList: List<Client>) {
        var spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, costumersList.map { it.name })
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spinnerLoadingClients) {
            adapter = spinnerAdapter
            setSelection(0, true)
            onItemSelectedListener = this@GenerateLoadingsFragment
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(context, "Nada selecionado", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val spinner = p0 as Spinner
        if (spinner.id == R.id.spinnerLoadingClients) {
            currentyClient = listClient[p2].id
        } else {
            currentyVehicle = listVehicleType[p2].type
        }
    }

    var date =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etLoadingDateRegister.setText(sdf.format(myCalendar.time))
    }

    var dateWithdrawal =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabelWithdrawal()
        }

    private fun updateLabelWithdrawal() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etLoadingWithdrawal.setText(sdf.format(myCalendar.time))
    }

    private fun enableButtonNext() {
        //btnLoadingNext.isEnabled = etLoadingQtd.text!!.isNotEmpty()
    }

}