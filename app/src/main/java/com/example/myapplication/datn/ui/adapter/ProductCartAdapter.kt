package com.example.myapplication.datn.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.ProductCardBinding
import com.example.myapplication.datn.databinding.ViewProductCartBinding
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.ui.MainFragmentDirections
import com.example.myapplication.datn.ui.base.BaseListAdapter
import com.example.myapplication.datn.ui.home.HomeViewModel
import com.example.myapplication.datn.utils.Logger
import com.example.myapplication.datn.utils.toStringFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductCartAdapter(val context: Context, val viewModel: HomeViewModel) :
    BaseListAdapter<DetailOrder>(ContactDiffUtils()) {

    var itemSelected: ((Int) -> Unit)? = null
    var detailChange: ((DetailOrder) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        val binding =
            ViewProductCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactHolder(binding, viewModel, this::onItemSelected, this::onChange)
    }

    override fun onBindViewHolder(holder: BaseItemViewHolder, position: Int) {
        getItem(position).let(holder::bind)
    }

    override fun onBindViewHolder(
        holder: BaseItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            payloads.forEach { _ ->

            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    private fun onItemSelected(position: Int) {
        itemSelected?.invoke(currentList[position].idsanpham)
    }

    private fun onChange(position: Int, number: Int) {
        val detail = currentList[position].copy(soluong = number)
        detailChange?.invoke(detail)
    }

    class ContactDiffUtils : BaseDiffUtilItemCallback<DetailOrder>() {

        override fun areItemsTheSame(oldItem: DetailOrder, newItem: DetailOrder): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DetailOrder, newItem: DetailOrder): Boolean {
            return oldItem == newItem
        }
    }

    inner class ContactHolder(
        itemBinding: ViewProductCartBinding,
        private val homeViewModel: HomeViewModel,
        itemCb: ((Int) -> Unit),
        change: ((Int, Int) -> Unit)
    ) : BaseItemViewHolder(itemBinding) {
        private val name = itemBinding.tvNameCardProductItemCart
        private val number = itemBinding.tvValueCardProductItemCart
        private val unit = itemBinding.tvUnitCardProductItemCart
        private val card = itemBinding.cardProductItem
        private val numberPicker = itemBinding.numberCardProductItemCart

        init {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                itemCb.invoke(adapterPosition)
                Logger.d(adapterPosition.toString())
//                itemSelected?.invoke(currentList[adapterPosition])
            }
            card.setOnClickListener {
                itemCb.invoke(adapterPosition)
            }
            numberPicker.changeNumber = {
                change.invoke(adapterPosition, it)
            }

        }

        override fun bind(data: DetailOrder) {
            GlobalScope.launch(Dispatchers.Main) {
                var res: Product? = null
                res = homeViewModel.getProduct(data.idsanpham)

                name.text = res?.ten
                unit.text = res?.donvi
                number.text =
                    context.resources.getString(R.string.vnd_format, data.dongia.toStringFormat())
                numberPicker.setNumber(data.soluong)

                val result = withContext(Dispatchers.Default) {

                }
            }
        }
    }

}