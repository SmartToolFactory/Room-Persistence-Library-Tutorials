package com.example.tutorial2crudoperations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tutorial2crudoperations.data.User;
import com.example.tutorial2crudoperations.data.source.UserDataSource;
import com.example.tutorial2crudoperations.data.source.local.UserDatabase;
import com.example.tutorial2crudoperations.data.source.local.UserLocalDataSource;
import com.example.tutorial2crudoperations.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private UserDataSource mUserLocalDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        UserDatabase userDatabase = UserDatabase.getInstance(getApplicationContext());
        mUserLocalDataSource = UserLocalDataSource.getInstance(userDatabase.userDao());

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Wick");
        user.setAge(44);

        binding.setUser(user);

    }


    public class Handler {
        public void onClick(User user) {

            mUserLocalDataSource.insertUser(user);
        }
    }


}
