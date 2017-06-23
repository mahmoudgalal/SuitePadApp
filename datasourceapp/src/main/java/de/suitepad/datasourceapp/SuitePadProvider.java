package de.suitepad.datasourceapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class SuitePadProvider extends ContentProvider {
    private static  final String RESOURCE_FILE_NAME = "data";
    private static String cachedData = null;
    public static  final String AUTHORITY = "de.suitepad.dataprovider";
    public static  final String MAIN_TABLE = "main";
    private static final String COLUMN_NAME = "data_column";
    private  static  final String TAG = SuitePadProvider.class.getSimpleName();

    public SuitePadProvider() {
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Always inserts/updates dummy data for testing
     * @param uri
     * @param values
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG,"insert called...");
        // TODO: Implement this to handle requests to insert a new row.
        long id = 0;
        loadDataFile();
        return Uri.parse(MAIN_TABLE + "/" + id);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        Log.d(TAG,"Provider onCreate called...");
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //throw new UnsupportedOperationException("Not yet implemented");
        MatrixCursor cursor = new MatrixCursor(new String []{COLUMN_NAME});
        if(cachedData == null)
            loadDataFile();
        cursor.addRow(new Object[]{cachedData});
        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void loadDataFile(){
        if(cachedData == null) {
            InputStream is = getContext().getResources().openRawResource(getContext().
                    getResources().getIdentifier(RESOURCE_FILE_NAME,
                    "raw", getContext().getPackageName()));
            int bufferSize = 1024;
            char[] buffer = new char[bufferSize];
            StringBuilder out = new StringBuilder();
            try {
                Reader in = new InputStreamReader(is, "UTF-8");
                for (; ; ) {
                    int rsz = in.read(buffer, 0, buffer.length);
                    if (rsz < 0)
                        break;
                    out.append(buffer, 0, rsz);
                }
                cachedData = out.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
