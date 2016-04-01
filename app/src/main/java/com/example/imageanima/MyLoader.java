package com.example.imageanima;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jhon on 2016/3/29.
 */
public class MyLoader  extends AsyncTaskLoader<ArrayList> {
    private Context context;

    public MyLoader(Context context) {
        super(context);
        this.context = context;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();       // Launch the background task
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();     // Attempt to cancel the current load taskif possible
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
    }
    @Override
    public ArrayList loadInBackground() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = null;
        ArrayList<String> list = new ArrayList<>();
        String path = MediaStore.Images.Media.DATA;
        String where = MediaStore.Images.Media.BUCKET_DISPLAY_NAME  + "= 'Camera'";
        cursor = context.getContentResolver().query(uri,new String[] {path},where,null,null);
        int num = cursor.getColumnIndex(path);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            String pa = cursor.getString(num);
            list.add(pa);
            Log.d("dd",pa);
        }
        return list;
    }
}
