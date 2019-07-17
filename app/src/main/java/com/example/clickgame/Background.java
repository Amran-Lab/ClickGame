package com.example.clickgame;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;

public class Background extends IntentService {
    Player player = new Player();
    final String MY_PREFS_NAME = "MyPlayerPrefsFile";
    int level;
    public Background() {
        super("Background");
    }

   // public Background(String name) {
       // super("Background");
    //}




    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.
    }
    protected void onHandleIntent(Intent intent) {



        try {

            level++;
            sendDataToActivity();




            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }


    }
    private void sendDataToActivity()
    {
        Intent sendLevel = new Intent();
        sendLevel.setAction("Get_Level");
        sendLevel.putExtra( "LEVEL_DATA",level);
        sendBroadcast(sendLevel);

    }








}