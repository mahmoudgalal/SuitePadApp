package de.suitepad.datasourceapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DataBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("DataBroadcastReceiver","onReceive called.....");
        Intent intent1 =  new Intent(context,DataManagementService.class);
        context.startService(intent1);
    }
}
