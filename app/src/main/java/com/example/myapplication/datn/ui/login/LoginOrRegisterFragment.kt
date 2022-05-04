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
import com.google.firebase.auth.PhoneAuthOptions
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

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


    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.tvLoginByPass -> findNavController().navigate(R.id.action_loginOrRegisterFragment_to_loginFragment)
            binding.btnNextLogin -> handleClickLogin()
        }
    }

    private fun handleClickLogin() {
        val action = LoginOrRegisterFragmentDirections.actionLoginOrRegisterFragmentToVerificationFragment(binding.edPhoneLogin.text.toString().toInt())
        findNavController().navigate(action)
    }

}