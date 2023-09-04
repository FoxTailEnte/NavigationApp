package com.example.navigationapp.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.navigationapp.databinding.ItemResultBinding
import com.example.navigationapp.repository.db.LocationEntity

class ResultHolder(
    private val binding: ItemResultBinding, private val delCallBack: (LocationEntity)-> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LocationEntity) = with(binding) {
        itemView.setOnLongClickListener {
            delCallBack.invoke(item)
            true
        }
        tvLat.text = item.latitude
        tvLon.text = item.longitude
        tvData.text = item.date
    }
}