package com.iatfei.streakalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) { //todo:check whether the service is enabled in the first place.
        long lastnotif = Time.getLastFire(context);
        long notifint = Time.LongInterval(context);
        long nextFire;
        if ((System.currentTimeMillis()-lastnotif) < notifint){
            nextFire = notifint-(System.currentTimeMillis()-lastnotif);}
        else
            nextFire = (10 * 60 * 1000);
        
        Intent intent1 = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        PendingIntent pendingIntentDelay = PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, (System.currentTimeMillis() + nextFire), notifint, pendingIntentDelay);

        //todo:last-streak-aware
    }
}
