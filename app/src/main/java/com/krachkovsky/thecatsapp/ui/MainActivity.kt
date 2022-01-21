package com.krachkovsky.thecatsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.krachkovsky.thecatsapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController =
            this.findNavController(R.id.nav_container)
        val navView: BottomNavigationView =
            findViewById(R.id.bottom_nav_view)

        navView.setupWithNavController(navController)
    }
}