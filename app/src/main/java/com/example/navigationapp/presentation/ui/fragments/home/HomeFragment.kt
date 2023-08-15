package com.example.navigationapp.presentation.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.navigationapp.R
import com.example.navigationapp.databinding.FragmentMainBinding
import com.example.navigationapp.presentation.ui.base.BaseFragment
import com.example.navigationapp.presentation.ui.fragments.result.ResultFragment
import com.example.navigationapp.repository.utils.setContentFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentMainBinding>() {
    private val vm: HomeViewModel by viewModels()

    override fun initializeBinding(): FragmentMainBinding =
        FragmentMainBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setState()
    }

    override fun setupListener() = with(binding) {
        btResult.setOnClickListener {
            requireActivity().setContentFragment(ResultFragment())
        }
        btStart.setOnClickListener {
            vm.startGetLocation()
        }
        btStop.setOnClickListener {
            vm.stopLocation()
        }
    }

    private fun setState() = with(binding) {
        lifecycleScope.launch {
            vm.stateFlow.collect {
                when(it) {
                    "Start" -> {
                        btStart.setBackgroundColor( resources.getColor(R.color.green))
                        btStart.isEnabled = false
                        btStart.isClickable = false
                        btStop.setBackgroundColor( resources.getColor(R.color.grey))
                        btStop.isEnabled = true
                        btStop.isClickable = true
                    }
                    "Stop" -> {
                        btStop.setBackgroundColor( resources.getColor(R.color.red))
                        btStop.isEnabled = false
                        btStop.isClickable = false
                        btStart.setBackgroundColor( resources.getColor(R.color.grey))
                        btStart.isEnabled = true
                        btStart.isClickable = true
                    }
                }
            }
        }
    }
}