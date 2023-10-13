package com.example.madt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class RSPGame extends AppCompatActivity {
    TextView total, winner, player, computer;
    Button rock, paper, scissor, save;
    int number, computer_score, player_score, tie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rspgame);
//       NAME OF FILE
        final String NAME_OF_FILE = getIntent().getStringExtra("username") + "_RSP.txt";
//        RANDOM CLASS FOR CALLING RANDOM NUMBERS
        Random random = new Random();
        total = findViewById(R.id.total);
        winner = findViewById(R.id.winner);
        player = findViewById(R.id.player);
        computer = findViewById(R.id.computer);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissor = findViewById(R.id.scissor);
        save = findViewById(R.id.save);
        rock.setOnClickListener(v -> {
//            RANDOM NUMBER BETWEEN 1 AND 3
            int ranNum = random.nextInt(3) + 1;
            number++;
//            NUMBER VARIABLE INCREASES BY 1 AFTER EVERY BUTTON CLICK
            total.setText("Total Games: " + number);
            player.setText(getIntent().getStringExtra("username") +": Rock");
            if (ranNum == 1){
//                IF COMPUTER AND PLAYER GETS ROCK, GAME TIES
                computer.setText("Computer: Rock");
                winner.setText("TIE!");
                tie++;
            }
            else if (ranNum == 2){
//                IF PLAYER GETS ROCK AND COMPUTER GETS PAPER, COMPUTER WINS
                computer.setText("Computer: Paper");
                winner.setText("Computer Won!");
                computer_score++;
            }
            else if (ranNum == 3){
//                IF PLAYER GETS ROCK AND COMPUTER GETS SCISSOR, PLAYER WINS
                computer.setText("Computer: Scissor");
                winner.setText(getIntent().getStringExtra("username") +" Won!");
                player_score++;
            }
        });

        paper.setOnClickListener(v -> {
//            RANDOM NUMBER BETWEEN 1 AND 3
            int ranNum = random.nextInt(3) + 1;
            number++;
            total.setText("Total Games: " + number);
            player.setText(getIntent().getStringExtra("username") +": Paper");
            if (ranNum == 1){
//                IF PLAYER GETS PAPER AND COMPUTER GETS ROCK, PLAYER WINS
                computer.setText("Computer: Rock");
                winner.setText(getIntent().getStringExtra("username") +" Won!");
                player_score++;
            }
            else if (ranNum == 2){
//                IF COMPUTER AND PLAYER GETS PAPER, GAME TIES
                computer.setText("Computer: Paper");
                winner.setText("TIE!");
                tie++;
            }
            else if (ranNum == 3){
//                IF PLAYER GETS PAPER AND COMPUTER GETS SCISSOR, COMPUTER WINS
                computer.setText("Computer: Scissor");
                winner.setText("Computer Won!");
                computer_score++;
            }
        });

        scissor.setOnClickListener(v -> {
//            RANDOM NUMBER BETWEEN 1 AND 3
            int ranNum = random.nextInt(3) + 1;
            number++;
            total.setText("Total Games: " + number);
            player.setText(getIntent().getStringExtra("username") +": Scissor");
            if (ranNum == 1){
//                IF PLAYER GETS SCISSOR AND COMPUTER GETS ROCK, COMPUTER WINS
                computer.setText("Computer: Rock");
                winner.setText("Computer Won!");
                computer_score++;
            }
            else if (ranNum == 2){
//                IF PLAYER GETS SCISSOR AND COMPUTER GETS PAPER, PLAYER WINS
                computer.setText("Computer: Paper");
                winner.setText(getIntent().getStringExtra("username") +" Won!");
                player_score++;
            }
            else if (ranNum == 3){
//                IF COMPUTER AND PLAYER GETS SCISSOR, GAME TIES
                computer.setText("Computer: Scissor");
                winner.setText("Scores are same, TIE!");
                tie++;
            }
        });

//        WHEN SAVE BUTTON IS CLICKED, THE FILE IS SAVED
        save.setOnClickListener(v -> {
            String outcome;
            FileOutputStream filOutStr = null;

            try{
                filOutStr = openFileOutput(NAME_OF_FILE, MODE_PRIVATE);
//                IF COMPUTER SCORES MORE THAN PLAYER
                if (player_score < computer_score){
                    outcome = "\nUsername: "+getIntent().getStringExtra("username")+
                            "\nTotal Games: "+number+
                            "\nWins by "+ getIntent().getStringExtra("username") +": "+player_score+
                            "\nWins by Computer: "+computer_score+
                            "\nTie: " + tie +
                            "\n\nWINNER: Computer by " + (computer_score - player_score) + " game(s)";
                }
//                IF PLAYER SCORES MORE THAN COMPUTER
                else  if (player_score > computer_score){
                    outcome = "\nUsername: "+getIntent().getStringExtra("username")+
                            "\nTotal Games: "+number+
                            "\nWins by "+ getIntent().getStringExtra("username") +": "+player_score+
                            "\nWins by Computer: "+computer_score+
                            "\nTie: " + tie +
                            "\n\nWINNER: "+getIntent().getStringExtra("username")+ " by " + (player_score - computer_score) + " game(s)";
                }
//                IF BOTH SCORES SAME
                else{
                    outcome = "\nUsername: "+getIntent().getStringExtra("username")+
                            "\nTotal Games: "+number+
                            "\nWins by "+ getIntent().getStringExtra("username") +": "+player_score+
                            "\nWins by Computer: "+computer_score+"\nTie: " + tie +"\n\nTIE!";
                }

                filOutStr.write(outcome.getBytes());
                Toast.makeText(RSPGame.this, "Saved to " + getFilesDir() + "/" + NAME_OF_FILE, Toast.LENGTH_LONG).show();

//                THROWING EXCEPTIONS/ERRORS
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally{
                if (filOutStr != null){
                    try{
                        filOutStr.close();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}