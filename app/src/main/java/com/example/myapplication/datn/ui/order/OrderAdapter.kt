package com.example.myapplication.datn.ui.order

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.datn.R
import com.example.myapplication.datn.databinding.CardOrderListBinding
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.ui.base.BaseListAdapter
import com.example.myapplication.datn.utils.Logger
import com.example.myapplication.datn.utils.toStringFormat

class OrderAdapter(val context: Context) :
    BaseListAdapter<Order>(ContactDiffUtils()) {

    var itemSelected: ((Order) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        val binding =
            CardOrderListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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


    class ContactDiffUtils : BaseDiffUtilItemCallback<Order>() {

        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }

    inner class ContactHolder(
        itemBinding: CardOrderListBinding,
        itemCb: ((Int) -> Unit)
    ) : BaseItemViewHolder(itemBinding) {
        private val id = itemBinding.tvIdOrderCard
        private val sum = itemBinding.tvSumOrderCard
        private val time = itemBinding.tvTimeOrderCard
        private val card = itemBinding.cardOrderItem

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

        override fun bind(data: Order) {
            id.text = context.resources.getString(R.string.order_id, data.id)
            sum.text =
                context.resources.getString(R.string.order_sum, data.tongtien.toStringFormat())
            time.text = context.resources.getString(R.string.order_time, data.thoigian)
        }
    }

}