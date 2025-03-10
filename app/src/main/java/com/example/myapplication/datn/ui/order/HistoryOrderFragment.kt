package com.example.myapplication.datn.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.databinding.FragmentHistoryOrderBinding
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.ui.MainFragmentDirections
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryOrderFragment : BaseFragment<FragmentHistoryOrderBinding>() {
    private val viewModel: CartViewModel by activityViewModels()
    private var adapter: OrderAdapter? = null
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHistoryOrderBinding {
        return FragmentHistoryOrderBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = OrderAdapter(requireContext())
    }

    override fun initView() {
        super.initView()
        binding.rcvHistory.adapter = adapter
    }

    override fun initAction() {
        super.initAction()
        adapter?.itemSelected = {
            val action = MainFragmentDirections.actionMainFragment2ToOrderDetailFragment2(it)
            findNavController().navigate(action)
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.historyResult.observe(viewLifecycleOwner) { list ->
            val listH = mutableListOf<Order>()
            list.forEach {
                if (it.typeOrder == 2) {
                    listH.add(it)
                }
            }
            if (listH.isEmpty())
                binding.tvNoOrderHistory.visibility = View.VISIBLE
            else
                binding.tvNoOrderHistory.visibility = View.GONE
            adapter?.submitList(listH)
        }
    }

    override fun initData(arguments: Bundle?) {
        super.initData(arguments)
        viewModel.getHistoryOrder()

    }
}