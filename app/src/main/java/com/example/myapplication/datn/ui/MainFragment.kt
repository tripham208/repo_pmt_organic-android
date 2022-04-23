package com.example.myapplication.datn.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentLoginBinding
import com.example.myapplication.datn.databinding.FragmentMainBinding
import com.example.myapplication.datn.ui.adapter.FragmentAdapter
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.utils.Logger
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun initAction() {

        binding.viewPager.adapter = FragmentAdapter(requireActivity())


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.navBottom.menu.getItem(position).isChecked = true
            }
        })

       // NavigationUI.setupWithNavController(binding.navBottom, binding.fragmentContainerView2.findNavController())

        binding.navBottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    binding.viewPager.setCurrentItem(0,true)
                    true
                }
                R.id.page_2 -> {
                    binding.viewPager.setCurrentItem(1,true)

                    true
                }
                R.id.page_5 -> {
                    binding.viewPager.setCurrentItem(2,true)
                    true
                }
                R.id.page_3 -> {
                    // Respond to navigation item 1 reselection
                    binding.viewPager.setCurrentItem(3,true)
                    true
                }
                R.id.page_4 -> {
                    binding.viewPager.setCurrentItem(4,true)
                    true
                }

                else -> false
            }
        }
    }

}