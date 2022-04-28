package com.example.myapplication.datn.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentHistoryOrderBinding
import com.example.myapplication.datn.databinding.FragmentNowOrderBinding
import com.example.myapplication.datn.ui.adapter.OrderAdapter
import com.example.myapplication.datn.ui.adapter.ProductFavoriteAdapter
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.cart.CartViewModel
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.utils.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryOrderFragment : BaseFragment<FragmentHistoryOrderBinding>() {
    private val viewModel: CartViewModel by activityViewModels()
    private var adapter = OrderAdapter()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHistoryOrderBinding {
        return FragmentHistoryOrderBinding.inflate(inflater, container, false)
    }


    override fun initView() {
        super.initView()
        binding.rcvHistory.adapter = adapter
    }

    override fun initAction() {
        super.initAction()
        adapter.itemSelected = {
            Logger.d(it.toString())
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.historyResult.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initData(arguments: Bundle?) {
        super.initData(arguments)
        viewModel.getHistoryOrder()

    }
}