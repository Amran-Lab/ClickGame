package com.example.clickgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.SeekBar;
import android.content.Context;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Player player = new Player();
    final String MY_PREFS_NAME = "MyPlayerPrefsFile";

    Context context;
    //LevelReceiver levelReceiver;

    private Button robotf;
    private Button robotw;
    private Button robote;
    private Button robotwi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();


        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - player.timeend;
        double elapsedSeconds = tDelta / 1000.0;
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText("Elapsed Time " +elapsedSeconds);
        player.earthcounter = player.earthcounter + elapsedSeconds * player.robotearth * player.factor;
        player.firecounter = player.firecounter + elapsedSeconds * player.robotfire * player.factor;
        player.watercounter = player.watercounter + elapsedSeconds * player.robotwater * player.factor;
        player.windcounter = player.windcounter + elapsedSeconds * player.robotwind * player.factor;


        count("Earth:", player.earthcounter, "earth_counter");
        count("Water:", player.watercounter, "water_counter");
        count("Fire:", player.firecounter, "fire_counter");
        count("Wind:", player.windcounter, "wind_counter");


        //levelReceiver = new LevelReceiver();

        //Intent intent = new Intent(this, Background.class);
        //startService(intent);




        //registerReceiver(levelReceiver, new IntentFilter("Fire Counter"));

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_main:
                        Toast.makeText(MainActivity.this, "Main", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.navigation_upgrades:

                        startActivity(new Intent(MainActivity.this, UpgradeActivity.class));
                        player.timeend = System.currentTimeMillis();
                        save();

                        break;
                    case R.id.navigation_hero:

                        Toast.makeText(MainActivity.this, "Hero", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.navigation_arena:
                        Toast.makeText(MainActivity.this, "Arena", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


        Button buttonf = findViewById(R.id.fire_button);
        buttonf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.fireup();
                count("Fire:", player.firecounter, "fire_counter");
                save();


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
                                          player.earthcounter = player.earthcounter + 0.1 * player.robotearth * player.factor;
                                          player.firecounter = player.firecounter + 0.1 * player.robotfire * player.factor;
                                          player.watercounter = player.watercounter + 0.1 * player.robotwater * player.factor;
                                          player.windcounter = player.windcounter + 0.1 * player.robotwind * player.factor;


                                          count("Earth:", player.earthcounter, "earth_counter");
                                          count("Water:", player.watercounter, "water_counter");
                                          count("Fire:", player.firecounter, "fire_counter");
                                          count("Wind:", player.windcounter, "wind_counter");

                                          //receiver = new WifiLevelReceiver();
                                          //registerReceiver(receiver, new IntentFilter("Get_Level"));

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

    public void save() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("Fire Counter", (int) player.firecounter);
        editor.putInt("Water Counter", (int) player.watercounter);
        editor.putInt("Earth Counter", (int) player.earthcounter);
        editor.putInt("Wind Counter", (int) player.windcounter);
        editor.putInt("Fire Robot", player.robotfire);
        editor.putInt("Water Robot", player.robotwater);
        editor.putInt("Earth Robot", player.robotearth);
        editor.putInt("Wind Robot", player.robotwind);
        editor.putLong("Time End",player.timeend);
        editor.putInt("Factor",player.factor);
        editor.putInt("Clicker",player.clicker);



        editor.apply();
    }

    public void load() {
        SharedPreferences pref = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        player.firecounter = pref.getInt("Fire Counter", 0);
        player.watercounter = pref.getInt("Water Counter", 0);
        player.earthcounter = pref.getInt("Earth Counter", 0);
        player.windcounter = pref.getInt("Wind Counter", 0);
        player.robotfire = pref.getInt("Fire Robot", 0);
        player.robotwater = pref.getInt("Water Robot", 0);
        player.robotearth = pref.getInt("Earth Robot", 0);
        player.robotwind = pref.getInt("Wind Robot", 0);
        player.timeend = pref.getLong("Time End",0);
        player.factor = pref.getInt("Factor",0);
        player.clicker = pref.getInt("Clicker",0);


    }




}
