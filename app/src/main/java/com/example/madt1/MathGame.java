package com.example.madt1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MathGame extends AppCompatActivity{
    TextView question;
    Button answer1, answer2, answer3, answer4;
    int totalQuizQuestions = MathQuizQuestions.mathQuestions.length;
    int point = 0, recentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        question = findViewById(R.id.question);

//        WHEN ANSWERS ARE CLICKED, THE QUESTION CHANGES AND THE POINT INCREASES BY 1 IF THE ANSWER IS RIGHT
        answer1.setOnClickListener(v -> {
            if (MathQuizQuestions.rightAnswers[recentQuestion].equals(answer1.getText().toString())){
                point++;
            }
            recentQuestion++;
            next();
        });

        answer2.setOnClickListener(v -> {
            if (MathQuizQuestions.rightAnswers[recentQuestion].equals(answer2.getText().toString())){
                point++;
            }
            recentQuestion++;
            next();
        });

        answer3.setOnClickListener(v -> {
            if (MathQuizQuestions.rightAnswers[recentQuestion].equals(answer3.getText().toString())){
                point++;
            }
            recentQuestion++;
            next();
        });

        answer4.setOnClickListener(v -> {
            if (MathQuizQuestions.rightAnswers[recentQuestion].equals(answer4.getText().toString())){
                point++;
            }
            recentQuestion++;
            next();
        });
        next();
    }


//    FUNCTION TO CHANGE QUESTION AFTER EVERY ANSWERS ATTEMPTED
    void next(){
        if (recentQuestion == totalQuizQuestions){
            quit();
            return;
        }
        question.setText(MathQuizQuestions.mathQuestions[recentQuestion]);
        answer1.setText(MathQuizQuestions.answerOptions[recentQuestion][0]);
        answer2.setText(MathQuizQuestions.answerOptions[recentQuestion][1]);
        answer3.setText(MathQuizQuestions.answerOptions[recentQuestion][2]);
        answer4.setText(MathQuizQuestions.answerOptions[recentQuestion][3]);
    }

//    FUNCTION TO END THE GAME AFTER 10 QUESTIONS AND DIALOGUE BOX IS SHOWN WITH OBTAINED MARKS OUT OF 10 AND OPTION TO RESTART THE GAME
    void quit(){
        String outcome = "";
        if(point > totalQuizQuestions*0.40){
            outcome = "PASSED!";
        }
        else{
            outcome = "FAILED!";
        }
        new AlertDialog.Builder(this)
                .setTitle(outcome)
                .setMessage("You got "+point+" out of "+totalQuizQuestions)
                .setPositiveButton("Replay Quiz", (dialogInterface, i) -> {
                    point = 0;
                    recentQuestion = 0;
                    next();
                })
                .setCancelable(false)
                .show();


//        AFTER GAME ENDS, THE FILE WILL BE SAVED ALONG WITH USERNAME.
        FileOutputStream fileOutputStream = null;
        String message;
//        USERNAME OF CURRENTLY LOGGED IN USER
        String username = getIntent().getStringExtra("username");
//        CURRENT DATE
        String date = getIntent().getStringExtra("date");
//        NAME OF FILE
        final String NAME_OF_FILE = username + "_MathGame.txt";
        try{
            fileOutputStream = openFileOutput(NAME_OF_FILE, MODE_PRIVATE);

//            IF PLAYER PASSES
            if (point > totalQuizQuestions*0.40){
                message = "PASSED!\n" + "Name: "+ username +
                        "\nPoints Achieved: " + point+
                        "\nDate Logged In: "+date;
            }

//            IF PLAYER FAILS
            else{
                message = "FAILED!\n" + "Name: "+ username +
                        "\nPoints Achieved: " + point+
                        "\nDate Logged In: "+date;
            }
            fileOutputStream.write(message.getBytes());
            Toast.makeText(MathGame.this, "Saved to " + getFilesDir() + "/" + NAME_OF_FILE, Toast.LENGTH_LONG).show();

//            THROWING EXCEPTIONS/ERRORS
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (fileOutputStream != null){
                try{
                    fileOutputStream.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}