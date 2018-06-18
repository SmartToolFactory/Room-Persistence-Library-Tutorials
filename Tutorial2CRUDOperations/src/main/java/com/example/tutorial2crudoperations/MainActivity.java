package com.example.tutorial2crudoperations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.tutorial2crudoperations.data.User;
import com.example.tutorial2crudoperations.data.source.UserDataSource;
import com.example.tutorial2crudoperations.data.source.local.UserDatabase;
import com.example.tutorial2crudoperations.data.source.local.UserLocalDataSource;
import com.example.tutorial2crudoperations.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private UserDataSource mUserLocalDataSource;
    private User mUser;
    private TextView tvSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        UserDatabase userDatabase = UserDatabase.getInstance(getApplicationContext());
        mUserLocalDataSource = UserLocalDataSource.getInstance(userDatabase.userDao());

        mUser = new User();
        mUser.setFirstName("John");
        mUser.setLastName("Wick");
        mUser.setAge(44);

        binding.setUser(mUser);
        binding.setHandlers(new Handler());
        tvSum = binding.tvSummary;

    }


    public class Handler {
        public void onButtonClick(View view) {
            mUserLocalDataSource.insertUser(mUser);

            User[] users = mUserLocalDataSource.loadAllUsersOlderThan(43);
            StringBuilder sb = new StringBuilder();
            sb.append("USERS\n");
            if (users != null && users.length > 0) {
                for (User user : users) {
                    sb.append("name:" + user.getFirstName() + ", surname: " + user.getLastName() + ", age:" + user.getAge() + "\n");
                }
                tvSum.setText(sb.toString());
            }
        }
    }


}
