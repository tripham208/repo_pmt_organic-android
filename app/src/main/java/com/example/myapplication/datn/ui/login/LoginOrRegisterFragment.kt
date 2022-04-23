package com.example.myapplication.datn.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentLoginOrRegisterBinding
import com.example.myapplication.datn.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginOrRegisterFragment : BaseFragment<FragmentLoginOrRegisterBinding>(),
    View.OnClickListener {

    val viewModel : UserViewModel by activityViewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginOrRegisterBinding {
        return FragmentLoginOrRegisterBinding.inflate(inflater, container, false)
    }

    override fun initAction() {
        super.initAction()
        binding.tvLoginByPass.setOnClickListener(this)
        binding.btnNextLogin.setOnClickListener(this)


      //  viewModel.getMarsPhotos()

    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.tvLoginByPass -> findNavController().navigate(R.id.action_loginOrRegisterFragment_to_loginFragment)
            binding.btnNextLogin -> handleClickLogin()
        }
    }

    private fun handleClickLogin() {
        findNavController().navigate(R.id.action_loginOrRegisterFragment_to_verificationFragment)
    }

}