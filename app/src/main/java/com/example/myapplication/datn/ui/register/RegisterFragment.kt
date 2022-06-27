package com.example.myapplication.datn.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentRegisterBinding
import com.example.myapplication.datn.databinding.FragmentVerificationBinding
import com.example.myapplication.datn.model.entity.User
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.login.UserViewModel
import com.example.myapplication.datn.ui.login.VerificationFragmentArgs
import com.example.myapplication.datn.utils.Checker
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
                username = binding.edAccountRegister.text.toString(),
                password = binding.edPassRegister.text.toString(),
                phone = args.phone,
                name = binding.edNameRegister.text.toString(),
                typeAccount = 1,
                email = binding.edEmailRegister.text.toString(),
                address = null
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
        viewModel.registerResult.observe(viewLifecycleOwner) {
            if (it == R.string.success) {
                Toast.makeText(context, resources.getString(R.string.success), Toast.LENGTH_LONG).show()
                Checker.HAS_USER = true
                findNavController().navigate(R.id.action_registerFragment_to_mainFragment2)
            } else {
                Toast.makeText(context,  resources.getString(it), Toast.LENGTH_LONG).show()
            }
        }
    }

}