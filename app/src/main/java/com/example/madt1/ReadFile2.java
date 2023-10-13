package com.example.madt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFile2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_files);
        TextView files = findViewById(R.id.files);
        files.setText(readFile(getApplicationContext()));
    }

    //    FUNCTION TO READ THE FILE
    private String readFile(Context cnt) {
        String content = "";

        try {
            InputStream inputStream = cnt.openFileInput(getIntent().getStringExtra("username") + "_MathGame.txt");
//            IF THE FILE IS NOT EMPTY
            if (inputStream != null) {
//                READING AVAILABLE TEXT FROM FILE
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String textFromFile = "";
                StringBuilder stringBuilder = new StringBuilder();

//                READING DATA UNTIL IT FINISHES
                while ((textFromFile = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(textFromFile);
                }
                inputStream.close();
                content = stringBuilder.toString();
            }
//            THROWING EXCEPTIONS/ERRORS
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Failed!",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Failed!",Toast.LENGTH_SHORT).show();
        }
        return content;
    }

}