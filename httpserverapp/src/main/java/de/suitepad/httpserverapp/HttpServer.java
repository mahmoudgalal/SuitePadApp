package de.suitepad.httpserverapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class HttpServer extends Service {
    private static  final String TAG = HttpServer.class.getSimpleName();
    private NanoServer nanoServer;
    private static  final String DATA_SRC_LAUNCH_ACTION = "de.suitepad.datasourceapp.DATA_SOURCE_REQUESTED_ACTION";
    public HttpServer() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Service Created...");
        //start data source service for downloading/preparing data
        Intent intent = new Intent(DATA_SRC_LAUNCH_ACTION);
        sendBroadcast(intent);
        init();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand Created...");
        return super.onStartCommand(intent, flags, startId);
    }

    private void init(){
        try {
            nanoServer = new NanoServer(this);
            Log.d(TAG,"Nano server running...");
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return  null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Http Service is closed");
        if (nanoServer != null)
            nanoServer.stop();
    }
}
