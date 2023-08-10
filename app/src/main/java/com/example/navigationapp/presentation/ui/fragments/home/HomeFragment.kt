package com.example.navigationapp.presentation.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.navigationapp.databinding.FragmentMainBinding
import com.example.navigationapp.presentation.ui.base.BaseFragment
import com.example.navigationapp.presentation.ui.fragments.result.ResultFragment
import com.example.navigationapp.repository.utils.setContentFragment


class HomeFragment : BaseFragment<FragmentMainBinding>() {
    private val vm: HomeViewModel by viewModels({requireActivity()}) { HomeViewModelFactory() }


    override fun initializeBinding(): FragmentMainBinding =
        FragmentMainBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    override fun setupListener() = with(binding) {
        btResult.setOnClickListener {
            requireActivity().setContentFragment(ResultFragment())
        }
    }
}