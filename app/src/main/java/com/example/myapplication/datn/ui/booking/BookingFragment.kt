package com.example.myapplication.datn.ui.booking

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentBookingBinding
import com.example.myapplication.datn.ui.adapter.OrderDetailAdapter
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.cart.CartFragmentArgs
import com.example.myapplication.datn.ui.cart.CartViewModel
import com.example.myapplication.datn.ui.dialog.InternetDialogFragment
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.ui.login.UserViewModel
import com.example.myapplication.datn.utils.Checker
import com.example.myapplication.datn.utils.Logger
import com.example.myapplication.datn.utils.toStringFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingFragment : BaseFragment<FragmentBookingBinding>() {
    private var adapter: OrderDetailAdapter? = null
    private val viewModel: CartViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private val args: CartFragmentArgs by navArgs()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBookingBinding {
        return FragmentBookingBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = OrderDetailAdapter(requireContext(), homeViewModel)
    }

    init {

    }

    override fun initView() {
        super.initView()
        binding.rcvBooking.adapter = adapter
    }

    override fun initAction() {
        super.initAction()
        binding.apply {
            progressBar.visibility = View.INVISIBLE
            txtDaBooking.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)


                activity?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                            // Display Selected date in textbox

                            (it as EditText).setText("$dayOfMonth/${monthOfYear + 1}/$year")

                        },
                        year,
                        month,
                        day
                    )
                }?.show()
            }
            tvAddressBooking.setOnClickListener {
                findNavController().navigate(R.id.action_bookingFragment_to_addressFragment)
            }
            btnOrderBooking.setOnClickListener {
                if (context?.let { it1 -> Checker.checkForInternet(it1) } == true) {
                    var note = ""
                    if (txtDaBooking.text.isNotBlank()) {
                        note += "Nhận ngày ${txtDaBooking.text}. "
                    }
                    if (radioButtonShop.isChecked) {
                        note += "Nhận tại cửa hàng"
                    }
                    note += txtNoteBooking.text

                    val booking = viewModel.cart.value?.copy(note = note, typeOrder = 3)
                    if (booking != null) {
                        viewModel.booking(booking)
                        progressBar.visibility = View.VISIBLE
                    }
                } else {
                    InternetDialogFragment().show(
                        childFragmentManager, InternetDialogFragment.TAG
                    )
                }

            }
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()

        viewModel.details.observe(viewLifecycleOwner) { list ->
            Logger.d(list.toString())
            adapter?.submitList(list)
            if (list.isNotEmpty()) {
                var sum = 0
                var quan = 0
                list.forEach {
                    sum += it.quantity * it.unitPrice
                    quan += it.quantity
                }

                binding.tvQuantityBooking.text =
                    resources.getString(R.string.quantity, quan)
            }

        }
        viewModel.cart.observe(viewLifecycleOwner) {
            if (it != null)
                binding.tvSumBooking.text =
                    resources.getString(R.string.vnd_format, it.sum.toStringFormat())

        }
        userViewModel.user.observe(viewLifecycleOwner) {
            if (it?.address != null)
                binding.tvAddressBooking.text = it.address
        }
        viewModel.orderBooking.observe(viewLifecycleOwner) {
            if (it != null) {
                val action =
                    BookingFragmentDirections.actionBookingFragmentToOrderDetailFragment2(it)
                findNavController().navigate(action)
            }
        }
    }

    override fun initData(arguments: Bundle?) {
        super.initData(arguments)
        viewModel.getCartDetail()
    }
}