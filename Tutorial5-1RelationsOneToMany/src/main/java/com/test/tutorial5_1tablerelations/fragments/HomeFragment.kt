package com.test.tutorial5_1tablerelations.fragments

import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.test.tutorial5_1tablerelations.R
import com.test.tutorial5_1tablerelations.data.AppDatabase
import com.test.tutorial5_1tablerelations.data.Pet
import com.test.tutorial5_1tablerelations.data.User
import com.test.tutorial5_1tablerelations.data.UserAndAllPets
import com.test.tutorial5_1tablerelations.data.UserDao
import com.test.tutorial5_1tablerelations.databinding.FragmentHomeBinding

import java.util.ArrayList
import java.util.Random


class HomeFragment : Fragment() {

    // Create binding object
    private var binding: FragmentHomeBinding? = null

     var petNameList: MutableList<String> = ArrayList()

     private lateinit var userDao: UserDao

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        petNameList.add("Whiskey")
        petNameList.add("Beer")
        petNameList.add("Vodka")
        petNameList.add("Wine")
        petNameList.add("Rom")
        petNameList.add("Champagne")


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Create binding object
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        user = User()
        user.name = "New User"
        user.email = "abc@xyz.com"
        binding!!.user = user

        // Create Database
        val appDatabase = Room.databaseBuilder(activity!!.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()

        // Get User DAO
        userDao = appDatabase.userDao()

        binding!!.btnAddUser.setOnClickListener { view ->

            // Generate Random pets
            val pets = generateRandomPets()

            // Add users and their pets to database
            userDao.insertUserAndPets(user, pets)

        }

        binding!!.btnGetPets.setOnClickListener { view ->
            getPetsById()


        }

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe changes on Database
        userDao.getUserAndAllPets().observe(this, Observer { userAndAllPets -> getUsers(userAndAllPets!!) })
    }

    private fun generateRandomPets(): List<Pet> {
        val random = Random()

        val pets = ArrayList<Pet>()

        val petCount = random.nextInt(3) + 1
        for (i in 0 until petCount) {
            val pet = Pet()
            pet.name = petNameList[random.nextInt(petNameList.size)]
            pets.add(pet)
        }
        return pets
    }


    private fun getPetsById() {
        val sb = StringBuilder()
        sb.append("*** PETS ***\n")


        try {
            val id = java.lang.Long.parseLong(binding!!.etUserId.text.toString())
            val pets = userDao.getPetsOwnedByUser(id)

            for (pet in pets) {
                sb.append(pet.name).append("; ")
            }

        } catch (e: Exception) {

            sb.append(e.message)
        }

        binding!!.tvPets.text = sb.toString()
    }

    private fun getUsers(userAndAllPets: List<UserAndAllPets>) {

        val sb = StringBuilder()

        sb.append("*** USERS ***\n")

        if ((userAndAllPets != null) and (userAndAllPets.size > 0)) {

            for (userWithPet in userAndAllPets) {
                val user = userWithPet.user
                val userPets = userWithPet.pets

                sb.append("User: ").append(user!!.name).append(", email: ").append(user.email)

                // Set pets
                if (userPets != null && userPets.size > 0) {
                    sb.append(", pets: ")

                    for (pet in userPets) {
                        sb.append(pet.name).append(", ")
                    }

                }

                sb.append("\n")

            }

            // Display Text
            binding!!.tvUsers.text = sb.toString()
        }

    }

    companion object {

        private val DATABASE_NAME = "user_db"
    }


}
