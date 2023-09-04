package com.example.navigationapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationapp.databinding.ItemResultBinding
import com.example.navigationapp.repository.db.LocationEntity

class ResultAdapter(private val delCallBack: (LocationEntity)-> Unit) : RecyclerView.Adapter<ResultHolder>() {
    private var list: MutableList<LocationEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ResultHolder(ItemResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)) {
            delCallBack.invoke(it)

        }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun submitList(item: List<LocationEntity>) {
        val diffUtil = ResultDiffUtill(list, item)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = item.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }
}