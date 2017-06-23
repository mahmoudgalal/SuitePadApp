package de.suitepad.httpserverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HttpServerReceiver extends BroadcastReceiver {

    private static  final String HTTP_SERVER_LAUNCH_ACTION = "de.suitepad.httpserverapp.HTTP_CONTENT_REQUESTED_ACTION";
    private static  final String HTTP_SERVER_STOP_ACTION = "de.suitepad.httpserverapp.CLOSE_HTTP_CONTENT_REQUEST_ACTION";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        //Starting the long running server service
        Intent intent1 = new Intent(context, HttpServer.class);
        if(intent.getAction().equals(HTTP_SERVER_LAUNCH_ACTION)) {
            context.startService(intent1);
        }else{
            //stop it
            context.stopService(intent1);
        }
    }
}
