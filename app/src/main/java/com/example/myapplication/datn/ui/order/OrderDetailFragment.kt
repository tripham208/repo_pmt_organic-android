package com.example.myapplication.datn.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentOrderDetailBinding
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.ui.adapter.OrderDetailAdapter
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.cart.CartViewModel
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.utils.Logger
import com.example.myapplication.datn.utils.toStringFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment<FragmentOrderDetailBinding>() {
    private var adapter: OrderDetailAdapter? = null
    private val viewModel: CartViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    private val args: OrderDetailFragmentArgs by navArgs()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrderDetailBinding {
        return FragmentOrderDetailBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = OrderDetailAdapter(requireContext(), homeViewModel)
    }

    override fun initView() {
        super.initView()
        binding.apply {
            rcvOrderDetail.adapter = adapter
            if (args.order != null) {
                tvSumOrder.text =
                    resources.getString(R.string.vnd_format, args.order!!.sum.toStringFormat())
                tvDeTailOrder.text = resources.getString(R.string.order_detail, args.order!!.id)
                if (args.order!!.note != null)
                    tvNote.text = "Note: ${args.order!!.note}"
                else
                    tvNote.text = "Note:"
            }
        }


    }

    override fun initAction() {
        super.initAction()
        adapter?.itemSelected = {
            GlobalScope.launch(Dispatchers.Main) {
                var res: Product? = null
                res = homeViewModel.getProduct(it)

                val action =
                    OrderDetailFragmentDirections.actionOrderDetailFragment2ToProductDetailFragment(
                        res
                    )
                findNavController().navigate(action)

                val result = withContext(Dispatchers.Default) {

                }
            }
        }
        binding.imgBtnBackDetailOrder.setOnClickListener {
            findNavController().navigate(R.id.action_orderDetailFragment2_to_mainFragment2)
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.historyResultDetail.observe(viewLifecycleOwner) { list ->
            Logger.d(list.toString())
            adapter?.submitList(list)
            var quan = 0
            list.forEach {
                quan += it.quantity
            }
            binding.tvQuantityOrderDetail.text =
                resources.getString(R.string.quantity, quan)
        }

    }

    override fun initDataSaveArgs() {
        super.initDataSaveArgs()
        Logger.d(args.order.toString())
        args.order?.let { viewModel.getDetailOrder(it.id) }
    }

    override fun initData(arguments: Bundle?) {
        super.initData(arguments)

    }
}