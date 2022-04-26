package com.example.myapplication.datn.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentHomeBinding
import com.example.myapplication.datn.ui.MainFragmentDirections
import com.example.myapplication.datn.ui.adapter.ProductAdapter
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.utils.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()

    private var adapter: ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ProductAdapter(requireContext())
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        super.initView()
        binding.rcvHome.adapter = adapter


        binding.include.button.setOnClickListener {
            //binding.drawer.open()
            findNavController().navigate(R.id.action_mainFragment2_to_productDetailFragment)
        }
        binding.apply {

        }

        /*
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
            }*/
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
        viewModel.data.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }
    }
}