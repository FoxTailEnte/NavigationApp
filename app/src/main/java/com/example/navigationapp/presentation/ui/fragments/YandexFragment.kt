package com.example.navigationapp.presentation.ui.fragments

import com.example.navigationapp.databinding.FragmentYandexBinding
import com.example.navigationapp.presentation.ui.base.BaseFragment

class YandexFragment : BaseFragment<FragmentYandexBinding>() {
    override fun initializeBinding(): FragmentYandexBinding =
        FragmentYandexBinding.inflate(layoutInflater)

}