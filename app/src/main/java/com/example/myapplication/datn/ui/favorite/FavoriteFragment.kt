package com.example.myapplication.datn.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.databinding.FragmentFavoriteBinding
import com.example.myapplication.datn.ui.MainFragmentDirections
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()

    private var adapter: ProductFavoriteAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProductFavoriteAdapter(requireContext())
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        super.initView()
        binding.rcvFavorite.adapter = adapter
    }

    override fun initAction() {
        super.initAction()
        adapter?.itemSelected = {
            val action = MainFragmentDirections.actionMainFragment2ToProductDetailFragment(it)
            findNavController().navigate(action)
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.favorite.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
            if (it.isEmpty()) {
                binding.tvNumFavorite.visibility = View.VISIBLE
            } else {
                binding.tvNumFavorite.visibility = View.GONE

            }
        }
    }
}