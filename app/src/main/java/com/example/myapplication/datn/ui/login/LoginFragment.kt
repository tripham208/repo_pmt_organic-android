package com.example.myapplication.datn.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentLoginBinding
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.utils.Checker
import com.example.myapplication.datn.utils.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(), View.OnClickListener {
    private val viewModel: UserViewModel by activityViewModels()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
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
        val username = binding.edUserName.text.toString()
        val password = binding.edPass.text.toString()

        viewModel.clickLogin(username, password)
    }

    override fun observerLiveData() {
        super.observerLiveData()

        viewModel.loginResult.observe(viewLifecycleOwner) {
            if (it == R.string.success) {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment2)
                Checker.HAS_USER = true
            }
            Toast.makeText(context, resources.getString(it), Toast.LENGTH_LONG).show()
            Logger.d(it.toString())
        }

    }

}