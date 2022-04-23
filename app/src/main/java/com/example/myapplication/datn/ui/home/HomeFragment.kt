package com.example.myapplication.datn.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentHomeBinding
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.utils.Logger


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        super.initView()
        binding.card.setOnClickListener {
            Logger.d("click")
        }
        binding.include.button.setOnClickListener {
            binding.drawer.open()
        }
        binding.topnav.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.draw1->{
                    Logger.d("item1")
                }
                R.id.draw2->{
                    Logger.d("item2")
                }
                R.id.draw3->{
                    Logger.d("item3")
                }
                1->{
                    Logger.d("item4")
                }
            }
            binding.drawer.close()
            true
        }
        binding.apply {
            topnav.menu.add(1,1,1,"item4")
        }
    }
}