package com.example.roompersistencelibrary.fragments;

import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roompersistencelibrary.R;
import com.example.roompersistencelibrary.data.AppDatabase;
import com.example.roompersistencelibrary.data.User;
import com.example.roompersistencelibrary.databinding.FragmentHomeBinding;

import java.util.List;


public class HomeFragment extends Fragment {

    private static final String DATABASE_NAME = "user_db";

    private User mUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create binding object
        final FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        mUser = new User();
        mUser.setName("No name");
        mUser.setEmail("abc@xyz.com");
        binding.setUser(mUser);

        // Create Database
        final AppDatabase appDatabase = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        binding.btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = appDatabase.userDao().insertUser(mUser);


                List<User> users = appDatabase.userDao().getAll();

                StringBuilder sb = new StringBuilder();
                if (users != null && users.size() > 0) {
                    sb.append("*** USERS ***\n");
                    for (User user : users) {
                        sb.append("User: " + user.getName() + ", email: " + user.getEmail() + "\n");
                    }
                    binding.tvUsers.setText(sb.toString());

                }
            }
        });
        return binding.getRoot();
    }


}
