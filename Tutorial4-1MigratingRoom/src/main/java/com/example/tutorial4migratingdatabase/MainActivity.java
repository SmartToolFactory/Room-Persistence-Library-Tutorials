package com.example.tutorial4migratingdatabase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorial4migratingdatabase.db.AppDatabase;
import com.example.tutorial4migratingdatabase.db.Student;
import com.example.tutorial4migratingdatabase.db.StudentDao;
import com.example.tutorial4migratingdatabase.db.User;
import com.example.tutorial4migratingdatabase.db.UserDao;

import java.util.List;
import java.util.Random;


/**
 * This is a sample to migrate Room from older db version
 * In 1st version only users table is included.
 * In 2nd version students table is added to database.
 */

public class MainActivity extends AppCompatActivity {

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use repository pattern for real samples, it's for educational purposes
        final AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());


        // In version 1 perform on users table
        UserDao userDao = appDatabase.userDao();

        // Check out user table entries
        displayUsers(userDao);

        // Insert a new User
        insertUser(userDao);

        // Clear User table
        deleteUsers(appDatabase, userDao);

        // In version 2 perform on students table

/*

         StudentDao studentDao = appDatabase.studentDao();

        displayStudents(studentDao);

        insertStudent(studentDao);

        deleteStudents(appDatabase, studentDao);
*/


    }

    // Methods to perform action on users table

    private void displayUsers(UserDao userDao) {
        StringBuilder sb = new StringBuilder();
        List<User> users = userDao.getAll();

        TextView textView = findViewById(R.id.textView);
        if (users != null && users.size() > 0) {
            for (User user : users) {
                sb.append("User: " + user + "\n");
            }
            textView.setText(sb.toString());
        }
    }

    private void insertUser(UserDao userDao) {
        Button buttonInsert = findViewById(R.id.buttonInsert);

        buttonInsert.setOnClickListener(v -> {

            int rand = random.nextInt(300);

            User userEntry = new User();
            userEntry.setName("User" + rand);
            userEntry.setEmail("user" + rand + "@abc.com");
            userDao.insertUser(userEntry);


            displayUsers(userDao);

        });
    }

    private void deleteUsers(AppDatabase appDatabase, UserDao userDao) {
        Button buttonDelete = findViewById(R.id.buttonDelete);


        buttonDelete.setOnClickListener(v -> {
            appDatabase.clearAllTables();
            displayUsers(userDao);
        });
    }

    // Methods to perform on students

    private void displayStudents(StudentDao studentDao) {
        StringBuilder sb = new StringBuilder();
        List<Student> students = studentDao.getAll();

        TextView textView = findViewById(R.id.textView);
        if (students != null && students.size() > 0) {
            for (Student student : students) {
                sb.append("Student: " + student + "\n");
            }
            textView.setText(sb.toString());
        }
    }

    private void insertStudent(StudentDao studentDao) {
        Button buttonInsert = findViewById(R.id.buttonInsert);

        buttonInsert.setOnClickListener(v -> {

            int rand = random.nextInt(300);

            Student studentEntry = new Student();
            studentEntry.setName("User" + rand);
            studentEntry.setStudentNumber(rand + 10);
            studentDao.insertStudent(studentEntry);

            displayStudents(studentDao);

        });
    }

    private void deleteStudents(AppDatabase appDatabase, StudentDao studentDao) {
        Button buttonDelete = findViewById(R.id.buttonDelete);


        buttonDelete.setOnClickListener(v -> {
            appDatabase.clearAllTables();
            displayStudents(studentDao);
        });
    }

}
