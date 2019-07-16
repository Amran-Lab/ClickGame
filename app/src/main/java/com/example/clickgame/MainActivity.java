package com.example.clickgame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.SeekBar;
import android.content.Context;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Player player = new Player();
    final String MY_PREFS_NAME = "MyPlayerPrefsFile";

    Context context;

    private Button robotf;
    private Button robotw;
    private Button robote;
    private Button robotwi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonf = findViewById(R.id.fire_button);
        buttonf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.fireup();
                count("Fire:", player.firecounter, "fire_counter");

            }


        });
        Button buttonw = findViewById(R.id.water_button);
        buttonw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.waterup();

                count("Water:", player.watercounter, "water_counter");
            }


        });
        Button buttone = findViewById(R.id.earth_button);
        buttone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.earthup();

                count("Earth:", player.earthcounter, "earth_counter");
            }


        });
        Button buttonwi = findViewById(R.id.wind_button);
        buttonwi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.windup();

                count("Wind:", player.windcounter, "wind_counter");
            }


        });
        robotf = findViewById(R.id.robot_fire);
        robotf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int cost = 100 * (int) (Math.pow(10, player.multiplier));
                int startup = cost - 1;
                if (player.firecounter > startup) {
                    player.firerobot();
                    player.firecounter = player.firecounter - cost;

                    robotf.setText("Fire Robot " + player.robotfire);

                } else {

                    Toast toast = Toast.makeText(getApplicationContext(), "You need 100 fire tokens to buy a fire robot ", Toast.LENGTH_SHORT);
                    toast.show();


                }

            }

        });

        robotw = findViewById(R.id.robot_water);
        robotw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int cost = 100 * (int) (Math.pow(10, player.multiplier));
                int startup = cost - 1;
                if (player.watercounter > startup) {
                    player.waterrobot();
                    player.watercounter = player.watercounter - cost;
                    robotw.setText("Water Robot " + player.robotwater);

                } else {

                    Toast toast = Toast.makeText(getApplicationContext(), "You need 100 water tokens to buy a water robot ", Toast.LENGTH_SHORT);
                    toast.show();

                }
            }

        });
        robote = findViewById(R.id.robot_earth);
        robote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int cost = 100 * (int) (Math.pow(10, player.multiplier));
                int startup = cost - 1;
                if (player.earthcounter > startup) {
                    player.earthrobot();
                    player.earthcounter = player.earthcounter - cost;
                    robote.setText("Earth Robot " + player.robotearth);
                } else {

                    Toast toast = Toast.makeText(getApplicationContext(), "You need 100 earth tokens to buy a earth robot ", Toast.LENGTH_SHORT);
                    toast.show();

                }

            }

        });
        robotwi = findViewById(R.id.robot_wind);
        robotwi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int cost = 100 * (int) (Math.pow(10, player.multiplier));
                int startup = cost - 1;
                if (player.windcounter > startup) {
                    player.windrobot();
                    player.windcounter = player.windcounter - cost;
                    robotwi.setText("Wind Robot " + player.robotwind);
                } else {

                    Toast toast = Toast.makeText(getApplicationContext(), "You need 100 wind tokens to buy a earth wind ", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });


        Timer t = new Timer();

        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {

                                      try {
                                          player.earthcounter = player.earthcounter + 0.1 * player.robotearth*player.factor;
                                          player.firecounter = player.firecounter + 0.1 * player.robotfire*player.factor;
                                          player.watercounter = player.watercounter + 0.1 * player.robotwater*player.factor;
                                          player.windcounter = player.windcounter + 0.1 * player.robotwind*player.factor;


                                          count("Earth:", player.earthcounter, "earth_counter");
                                          count("Water:", player.watercounter, "water_counter");
                                          count("Fire:", player.firecounter, "fire_counter");
                                          count("Wind:", player.windcounter, "wind_counter");

                                      } catch (Exception e) {
                                          e.printStackTrace();


                                      }


                                  }

                              },

                50,

                100);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                player.multiplier = seekBar.getProgress();
                String str = "You are purchasing " + (int) (Math.pow(10, player.multiplier)) + " robots, costing " + 100 * (int) (Math.pow(10, player.multiplier)) + " counters";
                TextView textView = (TextView) findViewById(R.id.seekText);
                textView.setText(str);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void count(String counter, double element, String textID) {
        int resID = getResources().getIdentifier(textID, "id", getPackageName());
        TextView textView = (TextView) findViewById(resID);
        int el = (int) element;
        String elem = Integer.toString(el);
        textView.setText(counter + " " + withSuffix(el));
    }

    public static String withSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.2f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp - 1));
    }


}
