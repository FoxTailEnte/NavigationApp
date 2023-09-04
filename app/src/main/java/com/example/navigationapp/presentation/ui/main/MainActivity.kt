package com.example.navigationapp.presentation.ui.main

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.navigationapp.R
import com.example.navigationapp.databinding.ActivityMainBinding
import com.example.navigationapp.presentation.ui.fragments.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var pLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.deleteItems()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, HomeFragment())
            .commit()
        registerPermissionListener()
        getPermission()
    }

    private fun getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            )
        }
    }

    private fun registerPermissionListener() {
        pLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                if (it[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                    it[Manifest.permission.ACCESS_COARSE_LOCATION] == true &&
                    it[Manifest.permission.POST_NOTIFICATIONS] == true
                ) {
                    Toast.makeText(this, "ACCESS PERMISSION", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "DENIED PERMISSION", Toast.LENGTH_SHORT).show()
                }
            }
    }
}