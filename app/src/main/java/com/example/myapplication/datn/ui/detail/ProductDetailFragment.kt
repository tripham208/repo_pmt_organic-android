package com.example.myapplication.datn.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.FragmentHomeBinding
import com.example.myapplication.datn.databinding.FragmentProductDetailBinding
import com.example.myapplication.datn.ui.MainFragmentArgs
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.utils.Logger
import com.example.myapplication.datn.utils.toStringFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {
    private val viewModel: HomeViewModel by activityViewModels()
    private val args: ProductDetailFragmentArgs by navArgs()
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
                val action =
                    ProductDetailFragmentDirections.actionProductDetailFragmentToMainFragment2(2)
                findNavController().navigate(action)
            }
            imgBtnAddFavoriteDetailProduct.setOnClickListener {
                val product = args.product
                if (product != null) {
                    if (product.favorite){
                        binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    }else{
                        binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_24)
                    }
                    viewModel.update(product.copy(favorite = !product.favorite))
                }
            }
            imgBtnCartDetailProduct.setOnClickListener {

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

                if (it?.favorite == true){
                    binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_24)
                }else{
                    binding.imgBtnAddFavoriteDetailProduct.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }
    }


}