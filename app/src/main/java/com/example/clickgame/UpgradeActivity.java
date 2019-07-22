package com.example.clickgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UpgradeActivity extends AppCompatActivity {
    Player player = new Player();
    final String MY_PREFS_NAME = "MyPlayerPrefsFile";
    Button click1;
    Button click2;
    Button click3;
    Button click4;
    Button robot1;
    Button robot2;
    Button robot3;
    Button robot4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        load();
        tranparancy(player.clicker);
        robottransparent();


        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.navigation_upgrades);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_main:

                        startActivity(new Intent(UpgradeActivity.this, MainActivity.class));
                        overridePendingTransition(0, 0);

                        break;
                    case R.id.navigation_upgrades:

                        Toast.makeText(UpgradeActivity.this, "Upgrades", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.navigation_hero:
                        Toast.makeText(UpgradeActivity.this, "Hero", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.navigation_arena:
                        Toast.makeText(UpgradeActivity.this, "Arena", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        Button zero1 = (Button) findViewById(R.id.zero);
        zero1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.firecounter = 0;
                player.watercounter = 0;
                player.windcounter = 0;
                player.earthcounter = 0;
                player.robotwind = 0;
                player.robotearth = 0;
                player.robotwater = 0;
                player.robotfire = 0;
                player.clicker = 1;
                player.factor = 1;
                player.f1 = Boolean.parseBoolean("False");
                player.f2 = Boolean.parseBoolean("True");
                player.f3 = Boolean.parseBoolean("True");
                player.f4 = Boolean.parseBoolean("True");
                save();

            }
        });
        Button grand = (Button) findViewById(R.id.grand);
        grand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.firecounter = 1000;
                player.watercounter = 1000;
                player.windcounter = 1000;
                player.earthcounter = 1000;
                player.robotwind = 0;
                player.robotearth = 0;
                player.robotwater = 0;
                player.robotfire = 10;
                player.factor = 1;
                save();


            }
        });
        Button rich = (Button) findViewById(R.id.rich);
        rich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.firecounter = 1000;
                player.watercounter = 1000;
                player.windcounter = 1000;
                player.earthcounter = 1000;
                player.robotwind = 100;
                player.robotearth = 100;
                player.robotwater = 100;
                player.robotfire = 100;
                player.factor = 1;
                save();

            }
        });
        click1 = (Button) findViewById(R.id.click1);

        click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.f1){

                    player.clicker = 5;
                    player.f1 = Boolean.parseBoolean("True");
                    player.f2 = Boolean.parseBoolean("False");
                    tranparancy(player.clicker);

                    save();

                }


            }
        });
        click2 = (Button) findViewById(R.id.click2);
        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.f2){

                    player.clicker = 10;
                    player.f2 = Boolean.parseBoolean("True");
                    player.f3 = Boolean.parseBoolean("False");
                    save();
                    tranparancy(player.clicker);

                }


            }
        });
        click3 = (Button) findViewById(R.id.click3);
        click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.f3){

                    player.clicker = 50;
                    player.f3 = Boolean.parseBoolean("True");
                    player.f4 = Boolean.parseBoolean("False");
                    save();
                    tranparancy(player.clicker);

                }


            }
        });
        click4 = (Button) findViewById(R.id.click4);
        click4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.f4){

                    player.clicker = 200;
                    player.f4 = Boolean.parseBoolean("True");
                    save();
                    tranparancy(player.clicker);

                }


            }
        });
        robot1 = (Button) findViewById(R.id.robot1);
        robot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player.factor==1){
                    player.factor = 5;
                    robottransparent();
                    save();
                }

            }
        });
        robot2 = (Button) findViewById(R.id.robot2);
        robot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player.factor==5){
                    player.factor = 10;
                    robottransparent();
                    save();
                }

            }
        });
        robot3 = (Button) findViewById(R.id.robot3);
        robot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player.factor==10){
                    player.factor = 50;
                    robottransparent();
                    save();
                }

            }
        });
        robot4 = (Button) findViewById(R.id.robot4);
        robot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player.factor==50){
                    player.factor = 100;
                    robottransparent();
                    save();
                }

            }
        });



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
        editor.putInt("Factor",player.factor);
        editor.putInt("Clicker",player.clicker);
        editor.putBoolean("Check 1",player.f1);
        editor.putBoolean("Check 2",player.f2);
        editor.putBoolean("Check 3",player.f3);
        editor.putBoolean("Check 4",player.f4);






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
        player.f1 = pref.getBoolean("Check 1",false);
        player.f2 = pref.getBoolean("Check 2",false);
        player.f3 = pref.getBoolean("Check 3",false);
        player.f4 = pref.getBoolean("Check 4",false);


    }
    public void tranparancy(int light){
        click1 = (Button) findViewById(R.id.click1);
        if (light>4){
            click1.getBackground().setAlpha(64);
        }
        click2 = (Button) findViewById(R.id.click2);
        if (light>9){
            click2.getBackground().setAlpha(64);
        }
        click3 = (Button) findViewById(R.id.click3);
        if (light>49){
            click3.getBackground().setAlpha(64);
        }
        click4 = (Button) findViewById(R.id.click4);
        if (light>99){
            click4.getBackground().setAlpha(64);
        }


    }
    public void robottransparent(){
        robot1 = (Button) findViewById(R.id.robot1);
        if (player.factor>1){
            robot1.getBackground().setAlpha(64);
        }
        robot2 = (Button) findViewById(R.id.robot2);
        if (player.factor>5){
            robot2.getBackground().setAlpha(64);
        }
        robot3 = (Button) findViewById(R.id.robot3);
        if (player.factor>10){
            robot3.getBackground().setAlpha(64);
        }
        robot4 = (Button) findViewById(R.id.robot4);
        if (player.factor>50){
            robot4.getBackground().setAlpha(64);
        }

    }

}
