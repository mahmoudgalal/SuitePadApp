package de.suitepad.httpserverapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import fi.iki.elonen.NanoHTTPD;

/**
 * Created by user on 22/06/2017.
 */

public class NanoServer extends NanoHTTPD {
    private static  final int PORT = 8765;
    private  static  final String TAG = NanoServer.class.getSimpleName();
    private  static final  String ORIGINAL_DATA_URL = "https://gist.githubusercontent.com/Rio517/5c95cc6402da8c5e37bc579111e14350/raw/b8ac727658a2aae2a4338d1cb7b1e91aca6288db/z_output.json";
    //determins whether we should forward the request to external URI
    private  boolean shouldForward = false;
    public static  final String AUTHORITY = "de.suitepad.dataprovider";
    private  Context context;

    public  NanoServer(Context context)throws IOException {
        super(PORT);
        this.context = context;
        start();
    }

    public NanoServer(int port) {
        super(port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        Log.d(TAG,"Serving new request...");

        Cursor cursor = context.getContentResolver().query(Uri.parse("content://" +AUTHORITY+"/main"),null,null,null,null);
        if(cursor != null ){
            while (cursor.moveToNext()) {
                String json = cursor.getString(0);
                Log.d(TAG,"Returning json:"+json);
                cursor.close();
                //only first row
                return newFixedLengthResponse(Response.Status.OK,"application/json",json);
            }
        }else{//Cursor is empty or there is an error in reading data
            //Forward the request to the external resource.This is just a fake mimic for cache miss situations
            Response r = newFixedLengthResponse(Response.Status.REDIRECT, MIME_HTML, "");
            r.addHeader("Location", ORIGINAL_DATA_URL);
            return  r;
        }
        Log.d(TAG,"Returning :Hello world ...!");
        return newFixedLengthResponse("Hello world ...!");
    }

    @Override
    public Response serve(String uri, Method method, Map<String, String> headers, Map<String, String> parms, Map<String, String> files) {
        return super.serve(uri, method, headers, parms, files);
    }
}
