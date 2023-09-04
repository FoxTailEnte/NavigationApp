package com.example.navigationapp.presentation.ui.fragments.result

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.navigationapp.R
import com.example.navigationapp.databinding.FragmentResultBinding
import com.example.navigationapp.presentation.ui.adapter.ResultAdapter
import com.example.navigationapp.presentation.ui.base.BaseFragment
import com.example.navigationapp.presentation.ui.fragments.yandex.YandexFragment
import com.example.navigationapp.repository.db.LocationEntity
import com.example.navigationapp.repository.utils.setContentFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>() {
    private val vm: ResultViewModel by viewModels()
    private val adapterResult by lazy {
        ResultAdapter {
            showDialog(it)
        }
    }


    override fun initializeBinding(): FragmentResultBinding =
        FragmentResultBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.getAllItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.materialToolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.toolbar.materialToolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.del) {
                vm.delAllItems()
            } else {
                requireActivity().setContentFragment(YandexFragment())
            }
            true
        }
        initRc()
    }

    private fun initRc() {
        binding.rcResult.adapter = adapterResult
        lifecycleScope.launch {
            vm.locationState.collect {
                adapterResult.submitList(it)
            }
        }
    }

    private fun showDialog(item: LocationEntity) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Удалить локацию")
        builder.setMessage("Вы уверены, что хотите удалить эту геолокацию?")
        builder.setPositiveButton("Да") { _, _ ->
            vm.delItem(item)
        }
        builder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

}