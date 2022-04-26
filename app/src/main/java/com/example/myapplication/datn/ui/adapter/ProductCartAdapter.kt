package com.example.myapplication.datn.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.datn.databinding.ProductCardBinding
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.ui.base.BaseListAdapter
import com.example.myapplication.datn.utils.Logger

class ProductCartAdapter : BaseListAdapter<Product>(ContactDiffUtils()) {

    var itemSelected: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        val binding =
            ProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactHolder(binding, this::onItemSelected)
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
        itemSelected?.invoke(currentList[position])
    }

    class ContactDiffUtils : BaseDiffUtilItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    inner class ContactHolder(
        itemBinding: ProductCardBinding,
        itemCb: ((Int) -> Unit)
    ) : BaseItemViewHolder(itemBinding) {
        private val name = itemBinding.itemCardName
        private val number = itemBinding.itemCardValue
        private val card = itemBinding.itemCard

        init {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                itemCb.invoke(adapterPosition)
                Logger.d(adapterPosition.toString())
//                itemSelected?.invoke(currentList[adapterPosition])
            }
            card.setOnClickListener {
                itemCb.invoke(adapterPosition)
            }

        }

        override fun bind(data: Product) {
            name.text = data.ten
            number.text = data.dongia.toString()
        }
    }

}