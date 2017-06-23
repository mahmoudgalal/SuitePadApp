package de.suitepad.datasourceapp;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * An {@link IntentService} subclass for handling asynchronous data preparation requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DataManagementService extends IntentService {
    private static  final String TAG = DataManagementService.class.getSimpleName();

    private Handler handler = new Handler();

    public DataManagementService() {
        super("DataManagementService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Data Service is Running...",Toast.LENGTH_LONG).show();
            }
        });
        //Prepare a shared Content Provider for dummy data
        //just invoke and the method will install the required dummy data silently
        if (intent != null) {
            Uri uri = getContentResolver().insert(Uri.parse("content://" +SuitePadProvider.AUTHORITY),null);
            Log.d(TAG,"Inserted Data At:"+uri.toString());


        }
    }

}
