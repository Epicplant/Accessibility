package cse340.askforhelp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Objects;

/* ********************************************************************************************** *
 * ********************************************************************************************** *
 *                      DO NOT EDIT THIS FILE, PLEASE, DO NOT EDIT THIS FILE                      *
 * ********************************************************************************************** *
 * ********************************************************************************************** */

public class MyReceiver extends BroadcastReceiver {
    /**
     * This method is called when the BroadcastReceiver is receiving an Intent broadcast.
     * @param context The context in which the receiver is running
     * @param intent The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String selectedAppInfo = String.valueOf(Objects.requireNonNull(intent.getExtras()).get(Intent.EXTRA_CHOSEN_COMPONENT));
        int startIndex = selectedAppInfo.indexOf('{') + 1;
        int endIndex = selectedAppInfo.indexOf('/', startIndex);
        String appPackage = selectedAppInfo.substring(startIndex, endIndex);
        Log.e("CSE340 myReceiver", selectedAppInfo);
        Log.e("CSE340 myReceiver", appPackage);
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("cse340.askforhelp.PREFERENCES", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("app_package", appPackage);
            editor.apply();
        } catch (Exception e) {
            //accessing shared preferences file failed
        }
    }
}