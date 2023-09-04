package com.example.navigationapp.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.navigationapp.repository.db.LocationEntity

class ResultDiffUtill(
    private val oldList: List<LocationEntity>,
    private val newList: List<LocationEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            else -> true
        }
    }
}