package ru.mirea.sulokhin.notebook;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.loader.content.AsyncTaskLoader;

import java.io.FileInputStream;
import java.io.IOException;

public class FileLoaderAsync extends AsyncTaskLoader<String>{
    private static final String LOG_TAG = FileLoaderAsync.class.getSimpleName();

    private String fileName;

    public static final String ARG_FILE_NAME = "MGER1";

    public FileLoaderAsync(@NonNull Context context, Bundle args)
    {
        super(context);
        if(args != null){
            fileName = args.getString(ARG_FILE_NAME);
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        Log.i(LOG_TAG,"Entry");

        String text = loadTextFromFile(fileName);
        return text;
    }

    private String loadTextFromFile(String fileName){
        FileInputStream fin = null;
        try {
            fin = getContext().openFileInput(fileName);

            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);

            String text = new String(bytes);

            Log.d(LOG_TAG, text);

            return text;
        } catch (IOException ex) {
            Log.e(LOG_TAG,ex.getMessage());
            return null;
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Log.e(LOG_TAG,ex.getMessage());
                return null;
            }
        }
    }
}
