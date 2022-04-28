package com.example.myapplication.datn.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.datn.ui.order.HistoryOrderFragment
import com.example.myapplication.datn.ui.order.NowOrderFragment
import com.example.myapplication.datn.utils.Logger

class HistoryFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    companion object {
        const val PAGE = 2
    }

    override fun getItemCount(): Int = PAGE


    override fun createFragment(position: Int): Fragment {
        Logger.d(position.toString())
        return when (position) {
            0 -> NowOrderFragment()
            1 -> HistoryOrderFragment()
            else -> HistoryOrderFragment()
        }
    }
}