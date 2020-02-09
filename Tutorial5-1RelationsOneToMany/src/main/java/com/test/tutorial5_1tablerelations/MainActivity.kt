package com.test.tutorial5_1tablerelations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.test.tutorial5_1tablerelations.fragments.HomeFragment



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_container, HomeFragment())
        ft.commit()
    }
}
