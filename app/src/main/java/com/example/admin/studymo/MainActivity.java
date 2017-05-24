package com.example.admin.studymo;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //instanciate object widgets
    //textview is pretty much a label kind of confusing and annoying comming frm c#
    TextView txtrollResult;

    Button rollbtn;

    int score;
    TextView scoreTxt;
    Random rand;

    int die1;
    int die2;
    int die3;

    ArrayList<Integer> dielist;

    //this array to hold all three dice images
    ArrayList<ImageView> diceImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //score set to zero
        score = 0;

        //score text
        scoreTxt = (TextView) findViewById(R.id.ScoreTxt);
        // link widgets to java and cast them
        txtrollResult = (TextView) findViewById(R.id.intro);
        rollbtn = (Button) findViewById(R.id.rollBtn);

        //toast
        Toast.makeText(getApplicationContext(), "welcome to dice out", Toast.LENGTH_SHORT).show();

        //instantiate random
        rand = new Random();

        //intantiate dielist
        dielist = new ArrayList<Integer>();

        //array for imgs that find the pics in the xml IMPORTANT---------
        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        //intantiate array of immages and add them to array list type image
        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);

    };


    //when the onclick event hapens it will pass in the view of the event
    public void rollDice(View v){

        //roll dice this makes random numbers for each die
        die1= rand.nextInt(6)+1;
        die2= rand.nextInt(6)+1;
        die3= rand.nextInt(6)+1;

        //clear array list
        dielist.clear();

        //add to list
        dielist.add(die1);
        dielist.add(die2);
        dielist.add(die3);

        for (int numOfdies=0; numOfdies < 3; numOfdies++ ){

            String Imagesfordice = "die_" + dielist.get(numOfdies) + ".png";

            try {
                //access images from assest and set as input stream
                InputStream stream = getAssets().open(Imagesfordice);
                //make temporoay drawable object and link to pics
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(numOfdies).setImageDrawable(d);

            }
            catch(IOException e){
                    e.printStackTrace();
                };

        }

        //make a string with die values
       // String numgenerated = "die 1 = " + die1 + ", die 2 = " + die2 + " and die 3 = " + die3 ;
        String numgenerated;
        if (die1 ==die2 && die1 ==die3){

           // Toast.makeText(getApplicationContext(),numgenerated,Toast.LENGTH_SHORT).show();
           int scoreDelta = die1 * 100;
            numgenerated = "You rolled a tripple!! :" + "You scored a : " + scoreDelta;
            score+=scoreDelta;
        }
           else if (die1 == die2 || die1 == die2 || die2 == die3){
            numgenerated = "you scored 50 points";
            score+=50;
        }
        else{
            numgenerated = "Roll again";
        }
        scoreTxt.setText("Score :" + score);

        //make toast of values
        //Toast.makeText(getApplicationContext(),numgenerated,Toast.LENGTH_SHORT).show();

        //set text of label to values
       txtrollResult.setText(numgenerated);


    }
}
