package com.example.parser.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.example.parser.R

class SettingsFragment : Fragment() {

//    lateinit var switcher: Switch
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        switcher = view.findViewById(R.id.switcher)
//
//        switcher.setOnCheckedChangeListener { _, isChecked ->
//
//            // if the button is checked, i.e., towards the right or enabled
//            // enable dark mode, change the text to disable dark mode
//            // else keep the switch text to enable dark mode
//            if (switcher.isChecked) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                switcher.text = "Disable dark mode"
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                switcher.text = "Enable dark mode"
//            }
//
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}