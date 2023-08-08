package com.example.navigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = requireActivity().findViewById<TextView>(R.id.textView)
        text.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.setCurrentNavFragment(newInstance())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}