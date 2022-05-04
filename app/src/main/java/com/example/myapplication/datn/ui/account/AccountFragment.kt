package com.example.myapplication.datn.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentAccountBinding
import com.example.myapplication.datn.databinding.FragmentPasswordBinding
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.cart.CartViewModel
import com.example.myapplication.datn.ui.login.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    private val viewModel: UserViewModel by activityViewModels()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAccountBinding {
        return FragmentAccountBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        super.initView()

    }

    override fun initAction() {
        super.initAction()
        binding.tvNameAccount.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment2_to_accountInformationFragment)
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.user.observe(viewLifecycleOwner) {
            binding.tvNameAccount.text = it.name
        }
    }
}