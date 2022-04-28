package com.example.myapplication.datn.ui.detail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentProductDetailBinding
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.cart.CartViewModel
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.utils.toStringFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {
    private val viewModel: HomeViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()
    private val args: ProductDetailFragmentArgs by navArgs()
    var cart: Order? = null
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProductDetailBinding {
        return FragmentProductDetailBinding.inflate(inflater, container, false)
    }

    override fun initAction() {
        super.initAction()
        binding.apply {
            imgBtnBackDetailProduct.setOnClickListener {
                /*val action =
                    ProductDetailFragmentDirections.actionProductDetailFragmentToMainFragment2(2)
                findNavController().navigate(action)*/
                //this@ProductDetailFragment
                findNavController().popBackStack()
            }
            imgBtnAddFavoriteDetailProduct.setOnClickListener {
                val product = args.product
                if (product != null) {
                    if (product.favorite) {
                        binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    } else {
                        binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_24)
                    }
                    viewModel.update(product.copy(favorite = !product.favorite))
                }
            }
            imgBtnCartDetailProduct.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment2_to_cartFragment)
            }
            btnAddCartDetail.setOnClickListener {
                if (cartViewModel.cart.value != null) {
                    val detail = cartViewModel.cart.value!!.id?.let { it1 ->
                        args.product?.id?.let { it2 ->
                            args.product?.dongia?.let { it3 ->
                                DetailOrder(
                                    id = null,
                                    idhoadon = it1,
                                    idsanpham = it2,
                                    soluong = binding.numberPicker2.getNumber(),
                                    dongia = it3,
                                    giamgia = null
                                )
                            }
                        }
                    }
                    if (detail != null) {
                        cartViewModel.insertDetail(detail)
                    }
                } else {
                    cartViewModel.insertCart(
                        Order(
                            id = 1,
                            idkhachhang = 1,
                            idnhanvien = 1,
                            tongtien = 0,
                            thoigian = null, thanhtoan = 0,
                            loaidon = 4
                        )
                    )
                    val detail = cartViewModel.cart.value!!.id?.let { it1 ->
                        args.product?.id?.let { it2 ->
                            args.product?.dongia?.let { it3 ->
                                DetailOrder(
                                    id = null,
                                    idhoadon = it1,
                                    idsanpham = it2,
                                    soluong = binding.numberPicker2.getNumber(),
                                    dongia = it3,
                                    giamgia = null
                                )
                            }
                        }
                    }
                    if (detail != null) {
                        cartViewModel.insertDetail(detail)
                    }
                }
                Toast.makeText(
                    context,
                    resources.getString(R.string.add_cart_done, args.product?.ten),
                    Toast.LENGTH_LONG
                ).show()
                val action =
                    ProductDetailFragmentDirections.actionProductDetailFragmentToMainFragment2(2)
                findNavController().navigate(action)
            }
        }
    }

    override fun initView() {
        super.initView()
        args.product.let {
            binding.apply {
                tvNameProductDetail.text = args.product?.ten
                tvValueProductDetail.text =
                    resources.getString(R.string.vnd_format, args.product?.dongia?.toStringFormat())
                tvDes.text =
                    Html.fromHtml(args.product?.mota, Html.FROM_HTML_MODE_COMPACT)

                if (it?.favorite == true) {
                    binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }

    }

    override fun observerLiveData() {
        super.observerLiveData()
        cartViewModel.cart.observe(viewLifecycleOwner) {
            cart = it
        }
    }

}