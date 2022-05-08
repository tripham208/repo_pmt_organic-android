package com.example.myapplication.datn.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentSearchBinding
import com.example.myapplication.datn.ui.favorite.ProductFavoriteAdapter
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()

    private val args: SearchFragmentArgs by navArgs()

    private var adapter: ProductFavoriteAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProductFavoriteAdapter(requireContext())
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        super.initView()
        binding.apply {
            rcvSearch.adapter = adapter
            if (args.type==null){
                tvTitleSearch.text =
                    resources.getString(R.string.search_by_text, args.text)
            }else{
                tvTitleSearch.text =
                    resources.getString(R.string.search_by_text, args.type!!.name)
            }

            imgBtnBackSearch.setOnClickListener {
                findNavController().popBackStack()
            }
            imgBtnCartSearch.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchFragmentToCartFragment(1)
                findNavController().navigate(action)
            }
        }
    }

    override fun initAction() {
        super.initAction()
        adapter?.itemSelected = {
            val action = SearchFragmentDirections.actionSearchFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.search.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
            if (it.isEmpty()) {
                binding.tvNoProductSearch.visibility = View.VISIBLE
            } else {
                binding.tvNoProductSearch.visibility = View.GONE

            }
        }
    }

    override fun initDataSaveArgs() {
        super.initDataSaveArgs()

        if (args.type != null) {
            viewModel.searchType(args.type!!.id)
        }else{
            args.text?.let { viewModel.search(it) }
        }
    }
}