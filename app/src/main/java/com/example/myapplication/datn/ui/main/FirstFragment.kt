package com.example.myapplication.datn.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentFirstBinding
import com.example.myapplication.datn.ui.address.AddressViewModel
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.dialog.InternetDialogFragment
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.ui.login.UserViewModel
import com.example.myapplication.datn.utils.Checker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()
    private val addressViewModel: AddressViewModel by activityViewModels()
    private val user: UserViewModel by activityViewModels()


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFirstBinding {
        return FragmentFirstBinding.inflate(inflater, container, false)

    }

    override fun initAction() {
        super.initAction()
        if (context?.let { Checker.checkForInternet(it) } == true) {
            viewModel.getData()
            addressViewModel.getData()
        } else {
            InternetDialogFragment().show(
                childFragmentManager, InternetDialogFragment.TAG
            )
        }
    }

    override fun observerLiveData() {
        super.observerLiveData()
        user.user.observe(viewLifecycleOwner) {
            if (it != null)
                Checker.HAS_USER = true
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it == false)
                findNavController().navigate(R.id.action_firstFragment_to_mainFragment2)
        }
    }
}