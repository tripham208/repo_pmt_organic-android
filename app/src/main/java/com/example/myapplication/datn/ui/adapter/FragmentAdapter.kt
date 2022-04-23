package com.example.myapplication.datn.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.datn.ui.account.AccountFragment
import com.example.myapplication.datn.ui.cart.CartFragment
import com.example.myapplication.datn.ui.favorite.FavoriteFragment
import com.example.myapplication.datn.ui.home.HomeFragment
import com.example.myapplication.datn.ui.order.HistoryFragment

class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    companion object {
        const val PAGE = 5
    }

    override fun getItemCount(): Int = PAGE


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> HistoryFragment()
            2 -> CartFragment()
            3 -> FavoriteFragment()
            4 -> AccountFragment()
            else -> AccountFragment()
        }
    }
}