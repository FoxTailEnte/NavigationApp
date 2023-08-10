package com.example.navigationapp.repository.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.navigationapp.R


const val FRAGMENT_TAG = "Fragment"
inline fun FragmentActivity.setContentFragment(
    f: () -> Fragment
): Fragment {
    val manager = supportFragmentManager
    return f().apply {
        manager.beginTransaction()
            .replace(R.id.containerView, this)
            .addToBackStack(FRAGMENT_TAG)
            .commit()
    }
}

/*inline fun FragmentActivity.setContentFragment(
    containerViewId: Int,
    backStack: Boolean = false,
    f: () -> Fragment
): Fragment {
    val manager = supportFragmentManager
    return f().apply {
        manager.beginTransaction()
            .replace(containerViewId, this)
            .addToBackStack(FRAGMENT_TAG)
            .commit()
    }
}*/


/*inline fun FragmentActivity.setContentFragment(
    containerViewId: Int,
    backStack: Boolean = false,
    f: () -> Fragment
): Fragment {
    val manager = supportFragmentManager
    return f().apply {
        manager.beginTransaction().let {
            if (backStack) {
                it.replace(containerViewId, this, tag)
                    .addToBackStack(tag).commitAllowingStateLoss()
            } else {
                it.replace(containerViewId, this, tag).commitAllowingStateLoss()
            }
        }
    }
}*/
