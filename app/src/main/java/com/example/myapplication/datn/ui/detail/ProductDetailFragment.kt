package com.example.myapplication.datn.ui.detail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.datn.R
import com.example.myapplication.datn.database.AppAPI
import com.example.myapplication.datn.databinding.FragmentProductDetailBinding
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.cart.CartViewModel
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.utils.Checker
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
                val action =
                    ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment(1)
                findNavController().navigate(action)
            }
            btnAddCartDetail.setOnClickListener {
                if (Checker.HAS_USER == false) {
                    findNavController().navigate(R.id.action_productDetailFragment_to_loginFragment)
                } else {
                    if (cartViewModel.cart.value != null) {
                        val detail = cartViewModel.cart.value!!.id.let { it1 ->
                            args.product?.id?.let { it2 ->
                                args.product?.price?.let { it3 ->
                                    DetailOrder(
                                        id = null,
                                        idOrder = it1,
                                        idProduct = it2,
                                        quantity = binding.numberPicker2.getNumber(),
                                        unitPrice = it3,
                                        discount = null
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
                        resources.getString(R.string.add_cart_done, args.product?.name),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun initView() {
        super.initView()
        args.product.let {
            binding.apply {
                tvNameProductDetail.text = args.product?.name
                tvValueProductDetail.text =
                    resources.getString(R.string.vnd_format, args.product?.price?.toStringFormat())
                tvDes.text =
                    Html.fromHtml(args.product?.description, Html.FROM_HTML_MODE_COMPACT)

                if (it?.favorite == true) {
                    binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
                Glide.with(this@ProductDetailFragment)
                    .load("${AppAPI.IMG_URL}${it?.image}")
                    .fitCenter().error(R.drawable.ic_baseline_error_24)
                    .placeholder(R.drawable.ic_baseline_downloading_24)
                    .into(imgDetailProduct);
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