package com.example.navigationapp.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<out VB : ViewBinding> : Fragment() {
    protected val binding: VB
        get() = _binding!!
    private var _binding: VB? = null


    open fun setupUi() = Unit
    open fun setupListener() = Unit

    abstract fun initializeBinding(): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = initializeBinding()
        return binding.root
    }
}