package com.example.myapplication.datn.ui.address

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentAddressBinding
import com.example.myapplication.datn.model.entity.address.District
import com.example.myapplication.datn.model.entity.address.Ward
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.login.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.xml.datatype.DatatypeConstants.MONTHS

@AndroidEntryPoint
class AddressFragment : BaseFragment<FragmentAddressBinding>() {
    private val viewModel: UserViewModel by activityViewModels()
    private val addressViewModel: AddressViewModel by activityViewModels()
    private var districts: List<District>? = null
    private var wards: List<Ward>? = null
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAddressBinding {
        return FragmentAddressBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        super.initView()

    }

    override fun initAction() {
        super.initAction()
        addressViewModel.getData()
        binding.apply {
            txtDistrict.onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    districts?.get(position)?.code?.let { addressViewModel.getWards(it) }
                }
            btnSaveAddress.setOnClickListener {
                val address =
                    "${txtStreet.text}, ${txtWard.text}, ${txtDistrict.text}, ${txtCity.text}"

                val user = viewModel.user.value?.copy(address = address)

                user?.let { it1 -> viewModel.updateUser(it1) }
                findNavController().popBackStack()

            }

        }


    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.user.observe(viewLifecycleOwner) {

        }
        addressViewModel.district.observe(viewLifecycleOwner) {
            districts = it
            val data = mutableListOf<String>()
            it.forEach { district ->
                data.add(district.name)
            }
            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, data)
            binding.txtDistrict.setAdapter(arrayAdapter)
        }
        addressViewModel.wards.observe(viewLifecycleOwner) {
            val data = mutableListOf<String>()
            it.forEach { district ->
                data.add(district.name)
            }
            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, data)
            binding.txtWard.setAdapter(arrayAdapter)
        }


    }
}