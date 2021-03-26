package com.dvndr.tictactoa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    // two player
    int Player_O = 0;
    int Player_X = 1;
    // check which is active
    int activePlayer = Player_O;
    // postion trace
    int[] position = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    // game is activ or not
    boolean startGame = true;

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         b1=findViewById(R.id.btn1);
         b2=findViewById(R.id.btn2);
         b3=findViewById(R.id.btn3);
         b4=findViewById(R.id.btn4);
         b5=findViewById(R.id.btn5);
         b6=findViewById(R.id.btn6);
         b7=findViewById(R.id.btn7);
         b8=findViewById(R.id.btn8);
         b9=findViewById(R.id.btn9);
         text=findViewById(R.id.text);
        // setsetOnClickListener on every button
         b1.setOnClickListener(this);
         b2.setOnClickListener(this);
         b3.setOnClickListener(this);
         b4.setOnClickListener(this);
         b5.setOnClickListener(this);
         b6.setOnClickListener(this);
         b7.setOnClickListener(this);
         b8.setOnClickListener(this);
         b9.setOnClickListener(this);
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        // check game status
        if(!startGame){
            return;}
        // grab all ids of button which clicked
        Button clickedButton = findViewById(v.getId());
//        get tag name
        int getTag = Integer.parseInt(v.getTag().toString());
        // check that is value null or not ( useful for trace button)
        if(position[getTag] != -1){
            return;
        }

        position[getTag] = activePlayer;
        // player actively change and change position
        if(activePlayer == Player_O){
            clickedButton.setText("O");
            activePlayer = Player_X;
            text.setText("X's Turn");
            clickedButton.setBackground(getDrawable(android.R.color.holo_red_light));
        }
        else {
            clickedButton.setText("X");
            activePlayer = Player_O;
            text.setText("O's Turn");

            clickedButton.setBackground(getDrawable(android.R.color.holo_green_light));
        }
        //start gamewinner method
        gameWinner();
    }

    private void gameWinner() {
        // all winning position
        int[][] winnerCheck = { {0,1,2},{3,4,5} , {6,7,8} , {1,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}};
        // check these positon through for loop
            for (int i = 0; i < 8; i++) {
                int val0 = winnerCheck[i][0];
                int val1 = winnerCheck[i][1];
                int val2 = winnerCheck[i][2];

                if (position[val0] == position[val1] && position[val1] == position[val2]) {
                    if (position[val0] != -1) {
                        // if all condition fullfill then it will be game over
                        startGame = false;
                        if (position[val0] == Player_O) {
                            alertBox("O IS WINNER");
                        } else {
                            alertBox("X IS WINNER");
                        }
                    }
                }
                else if (position[0] != -1 && position[1] != -1 &&position[2] != -1 &&position[3] != -1 &&position[4] != -1 &&position[5] != -1 &&
                        position[6] != -1 &&position[7] != -1 &&position[8] != -1 ){

                    alertBox("Match Draw!!!");
                    startGame = false;
                }
            }
        }


    private  void alertBox(String Message){
//        make Alertbox to show result
        new AlertDialog.Builder(this).setTitle(Message)
                .setPositiveButton("Restart", (dialog, which) -> {
                    restartGame();
                }).show();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void restartGame() {
        //reset all position ,button , color , array ,positions
        text.setText("O's Turn");
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");
        b1.setBackground(getDrawable(R.color.brown));
        b2.setBackground(getDrawable(R.color.brown));
        b3.setBackground(getDrawable(R.color.brown));
        b4.setBackground(getDrawable(R.color.brown));
        b5.setBackground(getDrawable(R.color.brown));
        b6.setBackground(getDrawable(R.color.brown));
        b7.setBackground(getDrawable(R.color.brown));
        b8.setBackground(getDrawable(R.color.brown));
        b9.setBackground(getDrawable(R.color.brown));
        position = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        activePlayer=Player_O;
        startGame=true;
    }
}

