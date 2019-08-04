package com.test.tutorial5_1tablerelations.fragments;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

        binding.btnAddUser.setOnClickListener(view -> {

            // Generate Random pets
            List<Pet> pets = generateRandomPets();

            // Add users and their pets to database
            userDao.insertUserAndPets(mUser, pets);

        });

        binding.btnGetPets.setOnClickListener(view -> {
            getPetsById();


        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Observe changes on Database
        userDao.getUserAndAllPets().observe(this, new Observer<List<UserAndAllPets>>() {
            @Override
            public void onChanged(@Nullable List<UserAndAllPets> userAndAllPets) {
                getUsers(userAndAllPets);
            }
        });
    }

    @NonNull
    private List<Pet> generateRandomPets() {
        Random random = new Random();

        List<Pet> pets = new ArrayList<>();

        int petCount = random.nextInt(3) + 1;
        for (int i = 0; i < petCount; i++) {
            Pet pet = new Pet();
            pet.name = petNameList.get(random.nextInt(petNameList.size()));
            pets.add(pet);
        }
        return pets;
    }


    private void getPetsById() {
        StringBuilder sb = new StringBuilder();
        sb.append("*** PETS ***\n");


        try {
            long id = Long.parseLong(binding.etUserId.getText().toString());
            List<Pet> pets = userDao.getPetsOwnedByUser(id);

            for (Pet pet : pets) {
                sb.append(pet.name).append("; ");
            }

        } catch (Exception e) {

            sb.append(e.getMessage());
        }

        binding.tvPets.setText(sb.toString());
    }

    private void getUsers(List<UserAndAllPets> userAndAllPets) {

        StringBuilder sb = new StringBuilder();

        sb.append("*** USERS ***\n");

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
