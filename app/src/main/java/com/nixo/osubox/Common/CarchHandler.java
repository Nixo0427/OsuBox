package com.nixo.osubox.Common;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.nixo.osubox.MainActivity;
import com.nixo.osubox.SplashActivity;

public class CarchHandler implements Thread.UncaughtExceptionHandler{

    public static CarchHandler mAppCrashHandler;

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private Application mAppContext;

    public void initCrashHandler(Application application) {
        this.mAppContext = application;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CarchHandler getInstance() {
        if (mAppCrashHandler == null) {
            mAppCrashHandler = new CarchHandler();
        }
        return mAppCrashHandler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

//        AlarmManager mgr = (AlarmManager) mAppContext.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(mAppContext, SplashActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("crash", true);
//        PendingIntent restartIntent = PendingIntent.getActivity(mAppContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
//        System.gc();

        if (!handleException(ex) && mDefaultHandler != null) {
//            // 如果用户没有处理则让系统默认的异常处理器来处理
        mDefaultHandler.uncaughtException(thread, null);

//
        } else {
            AlarmManager mgr = (AlarmManager) mAppContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(mAppContext, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("crash", true);
            PendingIntent restartIntent = PendingIntent.getActivity(mAppContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            System.gc();
        }
    }

    /**
     * 错误处理,收集错误信息 发送错误报告等操作均在此完成.
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 自定义处理错误信息
        return true;
    }
}
