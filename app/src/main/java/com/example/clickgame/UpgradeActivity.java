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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.navigation_upgrades);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_main:

                        startActivity(new Intent(UpgradeActivity.this, MainActivity.class));

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
                save();

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




        editor.apply();
    }

}
