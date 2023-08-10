package com.example.navigationapp.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.navigationapp.databinding.FragmentMainBinding
import com.example.navigationapp.presentation.ui.base.BaseFragment
import com.example.navigationapp.repository.utils.setContentFragment


class MainFragment : BaseFragment<FragmentMainBinding>() {


    override fun initializeBinding(): FragmentMainBinding =
        FragmentMainBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    override fun setupListener() = with(binding) {
        btResult.setOnClickListener {
            requireActivity().setContentFragment {
                ResultFragment()
            }
        }
    }
}