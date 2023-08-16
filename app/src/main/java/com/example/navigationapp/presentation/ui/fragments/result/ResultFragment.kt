package com.example.navigationapp.presentation.ui.fragments.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.navigationapp.databinding.FragmentResultBinding
import com.example.navigationapp.presentation.ui.base.BaseFragment
import com.example.navigationapp.presentation.ui.fragments.yandex.YandexFragment
import com.example.navigationapp.repository.utils.setContentFragment

class ResultFragment : BaseFragment<FragmentResultBinding>() {
    private val vm: ResultViewModel by viewModels({requireActivity()}) {ResultViewModelFactory()}


    override fun initializeBinding(): FragmentResultBinding =
        FragmentResultBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.materialToolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }





        binding.toolbar.materialToolbar.setOnMenuItemClickListener {
            requireActivity().setContentFragment(YandexFragment())
            true
        }
    }
}