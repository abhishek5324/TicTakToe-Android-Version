package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    int ap = 0;     // 0= circle, 1 = cross
    boolean gameA = true;
    int [] gp = {2,2,2,2,2,2,2,2,2};      // 2 means unplayed
    int [][] WinPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public void dropin(View view)
    {
        ImageView counter = (ImageView) view;
       // System.out.println(counter.getTag().toString());
        int tap = Integer.parseInt(counter.getTag().toString());


        if(gp[tap]==2 && gameA) {
            gp[tap] = ap;

            counter.setTranslationY(-1000f);

            if (ap == 0) {
                counter.setImageResource(R.drawable.cross);
                // counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
                ap = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                //   counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
                ap = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);

            for(int[] wps : WinPos)
            {
                if(gp[wps[0]]==gp[wps[1]] &&
                gp[wps[1]]==gp[wps[2]] &&
                gp[wps[0]] != 2)
                {
                    gameA = false;
                    String win  = "Circle";
                    if(gp[wps[0]]==0)
                    {
                        win = "Cross";
                    }
                    /*
                    else
                    {
                        Toast.makeText(this, " Player 2 WiNs" , Toast.LENGTH_SHORT).show();
                    }*/
                    TextView winnmsg = findViewById(R.id.winmsg);
                    winnmsg.setText(win + " has Won ");
                    LinearLayout layout =  findViewById(R.id.playagainL);
                    layout.setVisibility(View.VISIBLE);
                }
                else {
                    boolean gameiso = true;
                    for (int counter1 : gp) {

                        if (counter1 == 2) {
                            gameiso = false;
                        }
                    }

                    if (gameiso)
                    {
                        TextView winnmsg = findViewById(R.id.winmsg);
                        winnmsg.setText("It is a draw");
                        LinearLayout layout =  findViewById(R.id.playagainL);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playagain(View view)
    {
        gameA = true;
        LinearLayout layout =  (LinearLayout) findViewById(R.id.playagainL);
        layout.setVisibility(View.INVISIBLE);
        ap = 0;     // 0= circle, 1 = cross
          //gp = {2,2,2,2,2,2,2,2,2};      // 2 means unplayed
        for(int i = 0; i< gp.length;i++)
        {
            gp[i] = 2;
        }

       GridLayout grid = (GridLayout)findViewById(R.id.grid);
        for(int i =0 ;i< grid.getChildCount();i++)
        {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}