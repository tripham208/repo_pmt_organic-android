package com.example.myapplication.datn.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentLoginBinding
import com.example.myapplication.datn.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() , View.OnClickListener {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        super.initView()

    }

    override fun initAction() {
        super.initAction()
        binding.btnLogin.setOnClickListener(this)
        binding.tvSMS.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.tvSMS -> findNavController().navigate(R.id.action_loginFragment_to_loginOrRegisterFragment)
            binding.btnLogin -> handleClickLogin()
        }
    }

    private fun handleClickLogin() {

    }

}