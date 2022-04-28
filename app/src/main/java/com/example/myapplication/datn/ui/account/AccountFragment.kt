package com.example.myapplication.datn.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentAccountBinding
import com.example.myapplication.datn.databinding.FragmentPasswordBinding
import com.example.myapplication.datn.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAccountBinding {
        return FragmentAccountBinding.inflate(inflater,container,false)
    }
}