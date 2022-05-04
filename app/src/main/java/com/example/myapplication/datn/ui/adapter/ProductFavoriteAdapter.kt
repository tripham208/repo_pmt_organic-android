package com.example.myapplication.datn.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.datn.R
import com.example.myapplication.datn.database.AppAPI
import com.example.myapplication.datn.databinding.CardProductListBinding
import com.example.myapplication.datn.databinding.ProductCardBinding
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.ui.base.BaseListAdapter
import com.example.myapplication.datn.utils.Logger
import com.example.myapplication.datn.utils.toStringFormat

class ProductFavoriteAdapter(val context: Context) : BaseListAdapter<Product>(ContactDiffUtils()) {

    var itemSelected: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        val binding =
            CardProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        itemBinding: CardProductListBinding,
        itemCb: ((Int) -> Unit)
    ) : BaseItemViewHolder(itemBinding) {
        private val name = itemBinding.tvNameCardProductItem
        private val number = itemBinding.tvValueCardProductItem
        private val card = itemBinding.cardProductItem
        private val unit = itemBinding.tvUnitCardProductItem
        private val img = itemBinding.imgCardProductItem


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
            unit.text = data.donvi
            number.text =
                context.resources.getString(R.string.vnd_format, data.dongia.toStringFormat())
            Glide.with (context)
                .load ( "${AppAPI.IMG_URL}${data.anh}")
                .fitCenter()
                .into (img);
        }
    }

}