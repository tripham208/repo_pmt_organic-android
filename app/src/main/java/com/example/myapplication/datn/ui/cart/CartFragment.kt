package com.example.myapplication.datn.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentCartBinding
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.ui.MainFragmentDirections
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.ui.login.UserViewModel
import com.example.myapplication.datn.utils.Logger
import com.example.myapplication.datn.utils.toStringFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    private var adapter: ProductCartAdapter? = null
    private val viewModel: CartViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private val args: CartFragmentArgs by navArgs()
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

    init {

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
                if (findNavController().currentDestination?.id == R.id.mainFragment2) {
                    val action =
                        MainFragmentDirections.actionMainFragment2ToProductDetailFragment(res)
                    findNavController().navigate(action)
                } else {
                    val action =
                        CartFragmentDirections.actionCartFragmentToProductDetailFragment(res)
                    findNavController().navigate(action)
                }

                val result = withContext(Dispatchers.Default) {

                }

            }
        }
        binding.btnOrderCart.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.mainFragment2) {
                val action =
                    MainFragmentDirections.actionMainFragment2ToBookingFragment()
                findNavController().navigate(action)
            } else {
                val action =
                    CartFragmentDirections.actionCartFragmentToBookingFragment()
                findNavController().navigate(action)
            }
        }
        adapter?.detailChange = {
            viewModel.updateDetail(it)
        }
        adapter?.deleteSelected = {
            viewModel.deleteDetail(it)
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()

        viewModel.details.observe(viewLifecycleOwner) { list ->
            Logger.d(list.toString())
            adapter?.submitList(list)
            if (list.isNotEmpty()) {
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
                binding.scrollCart.visibility = View.VISIBLE
                binding.tvNoCart.visibility = View.GONE
            } else {
                val cart = viewModel.cart.value?.copy(tongtien = 0)
                if (cart != null) {
                    viewModel.updateCart(cart)
                }
                binding.scrollCart.visibility = View.INVISIBLE
                binding.tvNoCart.visibility = View.VISIBLE
            }

        }
        viewModel.cart.observe(viewLifecycleOwner) {
            if (it != null)
                binding.tvSumCart.text =
                    resources.getString(R.string.vnd_format, it.tongtien.toStringFormat())
            else
                binding.tvSumCart.text =
                    resources.getString(R.string.vnd_format, 0.toStringFormat())
        }
    }

    override fun initData(arguments: Bundle?) {
        super.initData(arguments)
        viewModel.getCartDetail()
    }
}