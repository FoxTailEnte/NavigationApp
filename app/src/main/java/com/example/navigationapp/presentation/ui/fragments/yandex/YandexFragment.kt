package com.example.navigationapp.presentation.ui.fragments.yandex

import androidx.fragment.app.viewModels
import com.example.navigationapp.databinding.FragmentYandexBinding
import com.example.navigationapp.presentation.ui.base.BaseFragment

class YandexFragment : BaseFragment<FragmentYandexBinding>() {
    private val vm: YandexViewModel by viewModels({requireActivity()}) { YandexViewModelFactory() }


    override fun initializeBinding(): FragmentYandexBinding =
        FragmentYandexBinding.inflate(layoutInflater)

}