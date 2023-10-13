package com.example.madt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    RadioGroup user_type;
    RadioButton user, admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        user_type = findViewById(R.id.user_type);
        user = findViewById(R.id.user);
        admin = findViewById(R.id.admin);
        login = findViewById(R.id.login);

        login.setOnClickListener(v -> {
//            USERNAME AND PASSWORD FIELDS MUST NOT BE EMPTY
            if (!TextUtils.isEmpty(username.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
//                IF THE RADIO BUTTON (USER OR ADMIN) IS CLICKED
                if (check_user_type() == true) {
//                    IF THE ADMIN RADIO BUTTON IS CLICKED
                    if (user_type_function() == "Admin") {

//                        PROVIDING USERNAME AND PASSWORD OF AN ADMIN
                        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//                            PASSING USERNAME BETWEEN ACTIVITIES
                            i.putExtra("username", username.getText().toString());
//                            PASSING PASSWORD BETWEEN ACTIVITIES
                            i.putExtra("password", password.getText().toString());
//                            PASSING USER'S TYPE BETWEEN ACTIVITIES
                            i.putExtra("user_type", user_type_function());
                            startActivity(i);
                            Toast.makeText(MainActivity.this, "Hello " + username.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
//                        IF CREDENTIALS ARE WRONG
                        else {
                            Toast.makeText(MainActivity.this, "Provided credentials are wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{

//                        PROVIDING USERNAME AND PASSWORD FOR USERS
                        if (username.getText().toString().equals("Tom") && password.getText().toString().equals("tom")) {
                            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//                            PASSING USERNAME BETWEEN ACTIVITIES
                            i.putExtra("username", username.getText().toString());
//                            PASSING PASSWORD BETWEEN ACTIVITIES
                            i.putExtra("password", password.getText().toString());
//                            PASSING USER'S TYPE BETWEEN ACTIVITIES
                            i.putExtra("user_type", user_type_function());
                            startActivity(i);
                            Toast.makeText(MainActivity.this, "Hello " + username.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else if (username.getText().toString().equals("Harry") && password.getText().toString().equals("harry")) {
                            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//                            PASSING USERNAME BETWEEN ACTIVITIES
                            i.putExtra("username", username.getText().toString());
//                            PASSING PASSWORD BETWEEN ACTIVITIES
                            i.putExtra("password", password.getText().toString());
//                            PASSING USER'S TYPE BETWEEN ACTIVITIES
                            i.putExtra("user_type", user_type_function());
                            startActivity(i);
                            Toast.makeText(MainActivity.this, "Hello " + username.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Provided credentials are wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Please select user type!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(MainActivity.this, "Fields should not be empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    FUNCTION TO CHECK IF THE RADIO BUTTONS ARE CLICKED OR NOT
    public Boolean check_user_type(){
        if (user_type.getCheckedRadioButtonId() == -1){
            return false;
        }
        else{
            return true;
        }
    }

//    FUNCTION TO CHECK IF THE RADIO BUTTON (ADMIN OR USER) IS CLICKED
    public String user_type_function(){
        if (user_type.getCheckedRadioButtonId() == R.id.user){
            return "User";
        }
        else{
            return "Admin";
        }
    }
}