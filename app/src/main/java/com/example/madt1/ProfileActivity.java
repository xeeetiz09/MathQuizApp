package com.example.madt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {
    TextView username, password, user_type, date;
    Button rsp_game, math_game, rsp_history, math_game_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Calendar currentDate = Calendar.getInstance();

        username = findViewById(R.id.username);
//        SHOWING USERNAME IN PROFILE
        username.setText("Username: " + getIntent().getStringExtra("username"));
        password = findViewById(R.id.password);
//        SHOWING PASSWORD IN PROFILE
        password.setText("Password: " + getIntent().getStringExtra("password"));
        user_type = findViewById(R.id.user_type);
//         SHOWING USER TYPE (ADMIN OR USER) IN PROFILE
        user_type.setText("User Type: " + getIntent().getStringExtra("user_type"));
        date = findViewById(R.id.date);
//        SHOWING DATE IN PROFILE
        date.setText("Date: " + DateFormat.getDateInstance().format(currentDate.getTime()));
        rsp_game = findViewById(R.id.rsp_game);
        math_game = findViewById(R.id.math_game);
        rsp_history = findViewById(R.id.rsp_history);
        math_game_history = findViewById(R.id.math_game_history);


//        ROCK SCISSOR PAPER GAME
        rsp_game.setOnClickListener(v -> {
            Intent i=new Intent(ProfileActivity.this, RSPGame.class);
//            PASSING USERNAME BETWEEN ACTIVITIES
            i.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(i);
        });


//        MATH QUIZ GAME
        math_game.setOnClickListener(v -> {
            Intent i=new Intent(ProfileActivity.this, MathGame.class);
//            PASSING USERNAME BETWEEN ACTIVITIES
            i.putExtra("username", getIntent().getStringExtra("username"));
//            PASSING DATE BETWEEN ACTIVITIES
            i.putExtra("date", DateFormat.getDateInstance().format(currentDate.getTime()));
            startActivity(i);
        });


//        ROCK SCISSOR PAPER GAME HISTORY OF LOGGED IN USER
        rsp_history.setOnClickListener(v -> {
            Intent i=new Intent(ProfileActivity.this, ReadFile1.class);
//            PASSING USERNAME BETWEEN ACTIVITIES
            i.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(i);
        });

//        MATH QUIZ GAME HISTORY OF LOGGED IN USER
        math_game_history.setOnClickListener(v -> {
            Intent i=new Intent(ProfileActivity.this, ReadFile2.class);
//            PASSING USERNAME BETWEEN ACTIVITIES
            i.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(i);
        });
    }
}