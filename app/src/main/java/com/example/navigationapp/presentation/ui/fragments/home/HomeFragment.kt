package com.example.navigationapp.presentation.ui.fragments.home

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.navigationapp.R
import com.example.navigationapp.databinding.AlertDialogLayoutBinding
import com.example.navigationapp.databinding.FragmentMainBinding
import com.example.navigationapp.presentation.ui.base.BaseFragment
import com.example.navigationapp.presentation.ui.fragments.result.ResultFragment
import com.example.navigationapp.repository.location.LocationService
import com.example.navigationapp.repository.utils.setContentFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
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
        permissionListener()
        setLocation()
    }

    override fun setupListener() = with(binding) {
        btResult.setOnClickListener {
            navigateToResult()
        }
        btStart.setOnClickListener {
            checkPermission()
        }
        btStop.setOnClickListener {
            vm.stopLocation()
        }
    }

    private fun checkPermission() {
        vm.checkPermission()
    }

    private fun navigateToResult() {
        requireActivity().setContentFragment(ResultFragment())
    }

    private fun permissionListener() {
        CoroutineScope(Dispatchers.Main).launch {
            vm.permissionState.collectLatest {
                if (it) {
                    val serviceIntent = Intent(requireContext(), LocationService::class.java)
                    ContextCompat.startForegroundService(requireContext(), serviceIntent)
                }
                else getUserSettingsPermission()
            }
        }
    }

    private fun getUserSettingsPermission() {
        val dialogBinding = AlertDialogLayoutBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireContext())
        val dialog = builder.setView(dialogBinding.root).show()
        dialogBinding.negativeButton.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.positiveButton.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", requireContext().packageName, null)
            intent.data = uri
            startActivity(intent)
            dialog.dismiss()
        }
    }

    private fun setLocation() {
        lifecycleScope.launch {
            vm.locationState.collect {
                binding.textView.text = it
            }
        }
    }

    private fun setState() = with(binding) {
        lifecycleScope.launch {
            vm.state.collect {
                when (it) {
                    HomeViewModel.BtState.START -> {
                        btStart.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.green
                            )
                        )
                        btStart.isEnabled = false
                        btStart.isClickable = false
                        btStop.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.grey
                            )
                        )
                        btStop.isEnabled = true
                        btStop.isClickable = true
                    }

                    HomeViewModel.BtState.STOP -> {
                        btStop.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.red
                            )
                        )
                        btStop.isEnabled = false
                        btStop.isClickable = false
                        btStart.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.grey
                            )
                        )
                        btStart.isEnabled = true
                        btStart.isClickable = true
                    }
                }
            }
        }
    }
}