package com.example.myapplication.datn.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentRegisterBinding
import com.example.myapplication.datn.databinding.FragmentVerificationBinding
import com.example.myapplication.datn.model.entity.User
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.login.UserViewModel
import com.example.myapplication.datn.ui.login.VerificationFragmentArgs
import com.example.myapplication.datn.utils.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    private val args: RegisterFragmentArgs by navArgs()
    val viewModel: UserViewModel by activityViewModels()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater, container, false)
    }

    override fun initAction() {
        super.initAction()
        binding.btnRegister.setOnClickListener {
            val user = User(
                id = null,
                name = "test",
                password = "test",
                username = "username",
                loaitaikhoan = 1,
                address = " ",
                email = " ",
                phone = 1,
                )
            viewModel.register(user)
        }
    }

    override fun initView() {
        super.initView()
        binding.apply {
            edPhoneRegister.setText(args.phone.toString())
        }
    }

    override fun initDataSaveArgs() {
        super.initDataSaveArgs()
    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.registerResult.observe(viewLifecycleOwner){
            Logger.d(it.toString())
        }
    }

}