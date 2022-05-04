package com.example.myapplication.datn.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentAccountInfomationBinding
import com.example.myapplication.datn.databinding.FragmentMainBinding
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.login.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountInformationFragment : BaseFragment<FragmentAccountInfomationBinding>() {
    private val viewModel: UserViewModel by activityViewModels()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAccountInfomationBinding {
        return FragmentAccountInfomationBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        super.initView()
        binding.apply {

        }
    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.user.observe(viewLifecycleOwner) {
            binding.apply {
                tvAddressAccDetail.text = it.address
                tvEmailAccDetail.text = it.email
                tvNameAccDetail.text = it.name
                tvPhoneAccDetail.text = it.phone.toString()
                tvUsernameAccDetail.text = it.username
            }
        }
    }

}