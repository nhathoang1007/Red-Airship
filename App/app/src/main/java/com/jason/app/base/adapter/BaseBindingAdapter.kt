package com.jason.app.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Nhat.vo on 17/11/2020.
 */
abstract class BaseBindingAdapter<V : ViewDataBinding, T>(val onClicked: ((T?) -> Unit)? = null) :
    RecyclerView.Adapter<BaseViewHolder<V>>() {

    val list: MutableList<T> = mutableListOf()
    var parentWidth = 0
    lateinit var context: Context

    protected abstract fun getViewBinding(parent: ViewGroup, viewType: Int): V
    abstract fun bindViewHolder(dataBinding: V, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        parentWidth = parent.measuredWidth
        context = parent.context
        val binding = getViewBinding(parent, viewType)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        bindViewHolder(holder.dataBinding, position)
    }

    open fun updateData(list: MutableList<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    open fun addData(data: T) {
        this.list.add(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}

open class BaseViewHolder<V : ViewDataBinding>(val dataBinding: V) :
    RecyclerView.ViewHolder(dataBinding.root) {
    open fun bind(position: Int) {}
}