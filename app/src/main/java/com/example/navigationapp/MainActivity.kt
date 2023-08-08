package com.example.navigationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.containerView, MainFragment(), "Main").commit()
    }

     fun setCurrentNavFragment(fragment: Fragment) {
         if (fragment is MainFragment) {
             supportFragmentManager.beginTransaction().replace(R.id.containerView, BlankFragment(), "Blank").commit()
         }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag("Blank") is BlankFragment) {
            supportFragmentManager.beginTransaction().replace(R.id.containerView, MainFragment(), "Main").commit()
        } else {
            this.finish()
        }
    }
}