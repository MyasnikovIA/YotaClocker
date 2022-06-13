package com.example.yotamodem.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.yotamodem.MainActivity;


public class OnBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
             Intent i = new Intent(context, MainActivity.class);
             i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivity(i);
             Toast.makeText(context, "Yota Modem Clocker", Toast.LENGTH_SHORT).show();
        }
    }
}
