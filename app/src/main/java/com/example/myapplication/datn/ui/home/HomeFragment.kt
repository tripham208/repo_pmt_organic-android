package com.example.myapplication.datn.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentHomeBinding
import com.example.myapplication.datn.model.entity.ProductType
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



        binding.apply {
            imgBtnMenuHome.setOnClickListener {
                drawerLayout.open()
            }
        }


            binding.topNav.setNavigationItemSelectedListener {
                it.isChecked = true
                when(it.itemId){
                    R.id.draw1->{
                        val action = MainFragmentDirections.actionMainFragment2ToSearchFragment(type = ProductType(4,"rau ăn lá",null),
                            text = null
                        )
                        findNavController().navigate(action)
                    }
                    R.id.draw2->{
                        val action = MainFragmentDirections.actionMainFragment2ToSearchFragment(type = ProductType(5,"rau ăn củ",null),
                            text = null
                        )
                        findNavController().navigate(action)
                    }
                    R.id.draw3->{
                        val action = MainFragmentDirections.actionMainFragment2ToSearchFragment(type = ProductType(9,"rau ăn quả",null),
                            text = null
                        )
                        findNavController().navigate(action)
                    }
                    R.id.draw4->{
                        val action = MainFragmentDirections.actionMainFragment2ToSearchFragment(type = ProductType(6,"thịt lợn",null),
                            text = null
                        )
                        findNavController().navigate(action)
                    }
                    R.id.draw5->{
                        val action = MainFragmentDirections.actionMainFragment2ToSearchFragment(type = ProductType(7,"thịt bò",null),
                            text = null
                        )
                        findNavController().navigate(action)
                    }
                    R.id.draw6->{
                        val action = MainFragmentDirections.actionMainFragment2ToSearchFragment(type = ProductType(8,"thủy hải sản",null),
                            text = null
                        )
                        findNavController().navigate(action)
                    }
                    R.id.draw7->{
                        val action = MainFragmentDirections.actionMainFragment2ToSearchFragment(type = ProductType(14,"trái cây nhập khẩu",null),
                            text = null
                        )
                        findNavController().navigate(action)
                    }

                }
                binding.drawerLayout.close()
                true
            }
            binding.apply {
                //topnav.menu.add(1,1,1,"item4")
            }
    }

    override fun initAction() {
        super.initAction()

        adapter?.itemSelected = {
            val action = MainFragmentDirections.actionMainFragment2ToProductDetailFragment(it)
            findNavController().navigate(action)
        }
        binding.include.button.setOnClickListener {
            //binding.drawer.open()
            val search = binding.include.editTextTextPersonName.text.toString()
            val action = MainFragmentDirections.actionMainFragment2ToSearchFragment(search)
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