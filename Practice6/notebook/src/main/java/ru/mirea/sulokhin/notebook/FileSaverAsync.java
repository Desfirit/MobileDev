package ru.mirea.sulokhin.notebook;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.loader.content.AsyncTaskLoader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSaverAsync extends AsyncTaskLoader<String>{
    private static final String LOG_TAG = FileSaverAsync.class.getSimpleName();

    private String fileName;
    private String fileContent;

    public static final String ARG_FILE_NAME = "MGER1";
    public static final String ARG_FILE_CONTENT = "MGER2";

    public FileSaverAsync(@NonNull Context context, Bundle args)
    {
        super(context);
        if(args != null){
            fileName = args.getString(ARG_FILE_NAME);
            fileContent = args.getString(ARG_FILE_CONTENT);
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

        if (saveTextInFile(fileName, fileContent))
            return "true";
        else
            return "false";
    }

    private boolean saveTextInFile(String fileName, String text){
        FileOutputStream outputStream;
        try {
            outputStream = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Log.e(LOG_TAG,e.getMessage());
            return false;
        }
        return true;
    }
}
