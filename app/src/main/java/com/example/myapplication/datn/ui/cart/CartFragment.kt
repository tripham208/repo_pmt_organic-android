package com.example.myapplication.datn.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentCartBinding
import com.example.myapplication.datn.databinding.FragmentLoginBinding
import com.example.myapplication.datn.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment  : BaseFragment<FragmentCartBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCartBinding {
        return FragmentCartBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        super.initView()
    }
}