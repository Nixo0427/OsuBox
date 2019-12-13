package com.nixo.osubox.Utils;


import android.util.Log;

public class LaucherTime {

    private static long sTime;

    public static void startTime(){
        sTime = System.currentTimeMillis();
        Log.e("App启动时间",System.currentTimeMillis()+"");
    }

    public static void endTime(){
        long cost = System.currentTimeMillis() - sTime;
        Log.e("App终止时间",System.currentTimeMillis()+"");
        Log.e("APP终止-启动 -> ",cost+"");
    }

}
