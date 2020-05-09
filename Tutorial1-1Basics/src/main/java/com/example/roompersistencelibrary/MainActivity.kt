package com.example.roompersistencelibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roompersistencelibrary.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(
            R.id.fragment_container,
            HomeFragment()
        )
        ft.commit()
    }
}