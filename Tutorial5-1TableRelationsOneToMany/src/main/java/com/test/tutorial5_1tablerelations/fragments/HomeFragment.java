package com.test.tutorial5_1tablerelations.fragments;

import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.tutorial5_1tablerelations.R;
import com.test.tutorial5_1tablerelations.data.AppDatabase;
import com.test.tutorial5_1tablerelations.data.Pet;
import com.test.tutorial5_1tablerelations.data.User;
import com.test.tutorial5_1tablerelations.data.UserAndAllPets;
import com.test.tutorial5_1tablerelations.data.UserDao;
import com.test.tutorial5_1tablerelations.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HomeFragment extends Fragment {

    // Create binding object
    private FragmentHomeBinding binding;

    List<String> petNameList = new ArrayList<>();

    private static final String DATABASE_NAME = "user_db";

    UserDao userDao;

    private User mUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        petNameList.add("Whiskey");
        petNameList.add("Beer");
        petNameList.add("Vodka");
        petNameList.add("Wine");
        petNameList.add("Rom");
        petNameList.add("Champagne");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Create binding object
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        mUser = new User();
        mUser.name = "New User";
        mUser.email = "abc@xyz.com";
        binding.setUser(mUser);

        // Create Database
        final AppDatabase appDatabase = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        // Get User DAO
        userDao = appDatabase.userDao();

        binding.btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Generate Random pets
                List<Pet> pets = generateRandomPets();

                // Add users and their pets to database
                userDao.insertUserAndPets(mUser, pets);

                getUsers(userDao);
            }
        });
        return binding.getRoot();
    }

    @NonNull
    private List<Pet> generateRandomPets() {
        Random random = new Random();

        List<Pet> pets = new ArrayList<>();

        int petCount = random.nextInt(3) + 1;
        for (int i = 0; i < petCount; i++) {
            Pet pet = new Pet();
//                    pet.userId = userId;
            pet.name = petNameList.get(random.nextInt(petNameList.size()));
            pets.add(pet);
        }
        return pets;
    }

    private void getUsers(UserDao userDao) {

        StringBuilder sb = new StringBuilder();

        sb.append("*** USERS ***\n");

//        List<User> users = userDao.getAll();
        List<UserAndAllPets> userAndAllPets = userDao.getUserAndAllPets();

        if (userAndAllPets != null & userAndAllPets.size() > 0) {

            for (UserAndAllPets userWithPet : userAndAllPets) {
                User user = userWithPet.user;
                List<Pet> userPets = userWithPet.pets;

                sb.append("User: ").append(user.name).append(", email: ").append(user.email);

                // Set pets
                if (userPets != null && userPets.size() > 0) {
                    sb.append(", pets: ");

                    for (Pet pet : userPets) {
                        sb.append(pet.name).append(", ");
                    }

                }

                sb.append("\n");

            }

            // Display Text
            binding.tvUsers.setText(sb.toString());
        }

    }


}
