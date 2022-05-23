package com.example.myapplication.datn.ui.cart

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.datn.R
import com.example.myapplication.datn.database.AppAPI
import com.example.myapplication.datn.databinding.ViewProductCartBinding
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Product
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
    var deleteSelected:  ((DetailOrder) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        val binding =
            ViewProductCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactHolder(binding, viewModel, this::onItemSelected, this::onChange,this::onItemDeleteSelected)
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
        itemSelected?.invoke(currentList[position].idProduct)
    }
    private fun onItemDeleteSelected(position: Int) {
        deleteSelected?.invoke(currentList[position])
    }

    private fun onChange(position: Int, number: Int) {
        val detail = currentList[position].copy(quantity = number)
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
        change: ((Int, Int) -> Unit),
        itemDelete: ((Int) -> Unit)
    ) : BaseItemViewHolder(itemBinding) {
        private val name = itemBinding.tvNameCardProductItemCart
        private val number = itemBinding.tvValueCardProductItemCart
        private val unit = itemBinding.tvUnitCardProductItemCart
        private val card = itemBinding.cardProductItem
        private val numberPicker = itemBinding.numberCardProductItemCart
        private val img = itemBinding.imgCardProductItemCart
        private val del = itemBinding.imgBtnDeleteCart

        init {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                itemCb.invoke(adapterPosition)
                Logger.d(adapterPosition.toString())
            }
            card.setOnClickListener {
                itemCb.invoke(adapterPosition)
            }
            numberPicker.changeNumber = {
                change.invoke(adapterPosition, it)
            }
            del.setOnClickListener {
                itemDelete.invoke(adapterPosition)
            }

        }

        override fun bind(data: DetailOrder) {
            GlobalScope.launch(Dispatchers.Main) {
                var res: Product? = null
                res = homeViewModel.getProduct(data.idProduct)

                name.text = res?.name
                unit.text = res?.unit
                number.text =
                    context.resources.getString(R.string.vnd_format, data.unitPrice.toStringFormat())
                numberPicker.setNumber(data.quantity)
                Glide.with (context)
                    .load ( "${AppAPI.IMG_URL}${res?.image}")
                    .fitCenter()
                    .into (img);
                val result = withContext(Dispatchers.Default) {

                }
            }
        }
    }

}