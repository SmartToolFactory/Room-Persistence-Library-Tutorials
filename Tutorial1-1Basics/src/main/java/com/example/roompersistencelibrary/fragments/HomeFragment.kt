package com.example.roompersistencelibrary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.roompersistencelibrary.R
import com.example.roompersistencelibrary.data.AppDatabase
import com.example.roompersistencelibrary.data.User
import com.example.roompersistencelibrary.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var mUser: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create binding object
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mUser = User()
        mUser!!.name = "No name"
        mUser!!.email = "abc@xyz.com"
        binding.user = mUser

        // Create Database
        val appDatabase = Room
            .databaseBuilder(
                activity!!.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
            .allowMainThreadQueries()
            .build()
        binding.btnAddUser.setOnClickListener {
            val id = appDatabase.userDao()!!.insertUser(mUser)
            val users =
                appDatabase.userDao()!!.all
            val sb = StringBuilder()
            if (users != null && users.size > 0) {
                sb.append("*** USERS ***\n")
                for (user in users) {
                    sb.append(
                        """
    User: ${user!!.name}, email: ${user.email}

    """.trimIndent()
                    )
                }
                binding.tvUsers.text = sb.toString()
            }
        }
        return binding.root
    }

    companion object {
        private const val DATABASE_NAME = "user_db"
    }
}