package de.suitepad.suitepadapp;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private static  final String HTTP_SERVER_LAUNCH_ACTION = "de.suitepad.httpserverapp.HTTP_CONTENT_REQUESTED_ACTION";
    private static  final String HTTP_SERVER_STOP_ACTION = "de.suitepad.httpserverapp.CLOSE_HTTP_CONTENT_REQUEST_ACTION";
    private  static  final String TAG = MainActivity.class.getSimpleName();
    private static  final String URL = "file:///android_asset/page.html";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Launching the HTTP server service
        Intent intent =  new Intent(HTTP_SERVER_LAUNCH_ACTION);
        sendBroadcast(intent);

        Log.d(TAG,"BroadCast sent .....");

        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.loadUrl(URL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy called .....");
        //Stop Http Server when not needed
        Intent intent =  new Intent(HTTP_SERVER_STOP_ACTION);
        sendBroadcast(intent);
    }
}
