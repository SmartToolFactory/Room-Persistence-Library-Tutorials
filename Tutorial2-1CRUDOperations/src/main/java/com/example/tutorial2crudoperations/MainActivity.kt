package com.example.tutorial2crudoperations

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tutorial2crudoperations.data.User
import com.example.tutorial2crudoperations.data.source.UserDataSource
import com.example.tutorial2crudoperations.data.source.UserRepository
import com.example.tutorial2crudoperations.data.source.UserRepository.Companion.getInstance
import com.example.tutorial2crudoperations.data.source.local.UserDatabase
import com.example.tutorial2crudoperations.data.source.local.UserLocalDataSource.Companion.getInstance
import com.example.tutorial2crudoperations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var user: User
    private var tvSum: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val userDatabase = UserDatabase.getInstance(applicationContext)
        val userLocalDataSource: UserDataSource = getInstance(userDatabase.userDao())

        userRepository = getInstance(userLocalDataSource)
        user = User()
        user.firstName = "John"
        user.lastName = "Wick"
        user.age = 44
        binding.user = user
        binding.handlers = Handler()
        tvSum = binding.tvSummary
    }

    inner class Handler {
        fun onButtonClick(view: View?) {

            userRepository.insertUser(user)
            val users =
                userRepository.loadAllUsersOlderThan(43)
            val sb = StringBuilder()
            sb.append("USERS\n")

            if (!users.isNullOrEmpty()) {
                for ((_, age, firstName, lastName) in users) {
                    sb.append(" name:$firstName, surname: $lastName, age:$age")
                }
                tvSum!!.text = sb.toString()
            }
        }
    }
}