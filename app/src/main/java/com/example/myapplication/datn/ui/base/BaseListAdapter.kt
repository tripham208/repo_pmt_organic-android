package com.example.myapplication.datn.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T>(diffCallback: BaseDiffUtilItemCallback<T> = BaseDiffUtilItemCallback()) :
    ListAdapter<T, BaseListAdapter<T>.BaseItemViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: BaseItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: BaseItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(getItem(position))
    }


    open inner class BaseItemViewHolder(itemBinding: ViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal open fun bind(data: T) = Unit
    }


    open class BaseDiffUtilItemCallback<T > : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem

        }

    }
}