package com.example.myapplication.datn.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentHistoryBinding
import com.example.myapplication.datn.databinding.FragmentVerificationBinding
import com.example.myapplication.datn.ui.adapter.FragmentAdapter
import com.example.myapplication.datn.ui.adapter.HistoryFragmentAdapter
import com.example.myapplication.datn.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(inflater, container, false)
    }

    override fun initAction() {
        super.initAction()
    }

    override fun initView() {
        super.initView()
        binding.viewPagerHistory.adapter = HistoryFragmentAdapter(requireActivity())
        TabLayoutMediator(binding.tabHistory, binding.viewPagerHistory) { tab, position ->
            when (position) {
                0 -> {
                    //tab.customView = LayoutInflater.from(context).inflate(R.layout.tab_item, null)
                    tab.text = "Hiện tại"

                }
                1 -> {
                    //tab.customView = LayoutInflater.from(context).inflate(R.layout.tab_item2, null)
                    tab.text = "Đã giao"
                }
            }
        }.attach()
    }
}