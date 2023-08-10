package com.example.navigationapp.repository.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.navigationapp.R


const val FRAGMENT_TAG = "Fragment"
fun FragmentActivity.setContentFragment(f: Fragment) {
    val manager = supportFragmentManager
    f.apply {
        manager.beginTransaction()
            .replace(R.id.containerView, this)
            .addToBackStack(FRAGMENT_TAG)
            .commit()
    }
}
