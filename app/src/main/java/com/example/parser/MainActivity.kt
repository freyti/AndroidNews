package com.example.parser

import android.app.SearchManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parser.fragments.DashFragment
import com.example.parser.fragments.InfoFragment
import com.example.parser.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.w3c.dom.Document
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val dashFragment = DashFragment()
    private val infoFragment = InfoFragment()
    private val settingsFragment = SettingsFragment()

      lateinit var bottom_navigation:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

          bottom_navigation = findViewById(R.id.bottom_navigation)

        replaceFragment(dashFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_baseline->replaceFragment(dashFragment)
                R.id.ic_baseline_info->replaceFragment(infoFragment)
                R.id.ic_baseline_settings->replaceFragment(settingsFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }
}
