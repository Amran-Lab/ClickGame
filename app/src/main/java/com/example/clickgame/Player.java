package com.example.clickgame;

import android.content.Context;
import android.content.SharedPreferences;

public class Player {
    public double firecounter = 80;
    public double watercounter = 80;
    public double earthcounter = 1000;
    public double windcounter = 90;
    public int robotfire = 2;
    public int robotwater = 2;
    public int robotearth = 0;
    public int robotwind = 2;
    public  int multiplier=0;
    public int factor = 1;
    public long timeend = 0;
    public boolean f1 = Boolean.parseBoolean("False");
    public boolean f2 = Boolean.parseBoolean("False");
    public boolean f3 = Boolean.parseBoolean("False");
    public boolean f4 = Boolean.parseBoolean("False");





    public void fireup() {
        firecounter++;


    }
    public void waterup() {
        watercounter++;

    }
    public void earthup() {
        earthcounter++;

    }
    public void windup() {
        windcounter++;

    }
    public void firerobot() {
        robotfire = robotfire + (int)(Math.pow(10, multiplier));



    }
    public void waterrobot() {
        robotwater= robotwater +(int)(Math.pow(10, multiplier));

    }
    public void earthrobot() {
        robotearth =  robotearth + (int)(Math.pow(10, multiplier));

    }
    public void windrobot() {
        robotwind= robotearth + (int)(Math.pow(10, multiplier));

    }




}
