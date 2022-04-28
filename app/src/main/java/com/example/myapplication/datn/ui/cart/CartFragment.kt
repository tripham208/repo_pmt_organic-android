package com.example.myapplication.datn.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentCartBinding
import com.example.myapplication.datn.databinding.FragmentLoginBinding
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.ui.MainFragmentDirections
import com.example.myapplication.datn.ui.adapter.ProductAdapter
import com.example.myapplication.datn.ui.adapter.ProductCartAdapter
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.utils.Logger
import com.example.myapplication.datn.utils.toStringFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    private var adapter: ProductCartAdapter? = null
    private val viewModel: CartViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCartBinding {
        return FragmentCartBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ProductCartAdapter(requireContext(), homeViewModel)
    }

    override fun initView() {
        super.initView()
        binding.rcvCart.adapter = adapter
    }

    override fun initAction() {
        super.initAction()
        adapter?.itemSelected = {

            GlobalScope.launch(Dispatchers.Main) {
                var res: Product? = null
                res = homeViewModel.getProduct(it)

                val action = MainFragmentDirections.actionMainFragment2ToProductDetailFragment(res)
                findNavController().navigate(action)

                val result = withContext(Dispatchers.Default) {

                }

            }
        }
        adapter?.detailChange = {
            viewModel.updateDetail(it)
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.details.observe(viewLifecycleOwner) { list ->
            adapter?.submitList(list)
            if (list != null) {
                var sum = 0
                var quan = 0
                list.forEach {
                    sum += it.soluong * it.dongia
                    quan += it.soluong
                }
                val cart = viewModel.cart.value?.copy(tongtien = sum)
                if (cart != null) {
                    viewModel.updateCart(cart)
                }
                binding.tvQuantityCart.text =
                    resources.getString(R.string.quantity, quan)
            }

        }
        viewModel.cart.observe(viewLifecycleOwner) {
            if (it != null)
                binding.tvSumCart.text =
                    resources.getString(R.string.vnd_format, it.tongtien.toStringFormat())
            else
                binding.tvSumCart.text =
                    resources.getString(R.string.vnd_format,0.toStringFormat())
        }
    }
}